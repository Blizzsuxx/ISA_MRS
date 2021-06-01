<template>
<NavMeniZaPacijenta/>
<div id="map" class="map">
      <div id="popup" class="ol-popup" ref="container">
        <a href="#" id="popup-closer" class="ol-popup-closer" ref="closer"></a>
        <div id="popup-content" ref="content"></div>
      </div>
    </div>
<el-main>

<h2>Sve registrovane apoteke</h2>
<div style="margin-top: 20px">
    <!-- <el-button @click="toggleSelection([tableData[1], tableData[2]])">Toggle selection status of second and third rows</el-button> -->
    <el-input placeholder="Search" v-model="input"></el-input>
    <button v-on:click="pretrazi">Pretrazi</button>
	
  </div>
  <el-table
    ref="zaTabelu"
    :data="zaTabelu"
    style="width: 100%">
    <el-table-column
      property="id"
      label="ID"
      width="120">
      <template #default="scope">
      <router-link tag="button" :to="{name:'ProfilApotekeKorisnik',params:{id:scope.row.id}}">
        {{scope.row.id}}
      </router-link>
      </template>
    </el-table-column> 
    <el-table-column
      property="ime"
      label="Naziv"
      width="120">
    </el-table-column>
    <el-table-column
      property="mjesto"
      label="Mesto"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      property="ulica"
      label="Ulica"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      property="broj"
      label="Broj"
      show-overflow-tooltip>
    </el-table-column>
   <el-table-column
      property="ocena"
      label="Ocena"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      property="udaljenost"
      label="Udaljenost"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      align="right"> 



      <template #default="scope">
        <el-popconfirm
        confirmButtonText='Da'
        cancelButtonText='Ne'
        icon="el-icon-info"
        iconColor="red"
        title="Zelite da se pretplatite na izabranu apoteku?"
        @confirm='pretplata(scope.$index, scope.row)'>
        <template #reference>
        <el-button
          size="mini"
          type="danger"
          :disabled="scope.row.pretplacen">{{scope.row.pretplacen?'Pretplacen':'Pretplata'}}</el-button>
        </template>
        </el-popconfirm>
      </template>

    </el-table-column>
  </el-table>
</el-main>
</template>

<style scoped>

      .map {
        width: 100%;
        height:400px;
      }
      .ol-popup {
        position: absolute;
        background-color: white;
        box-shadow: 0 1px 4px rgba(0,0,0,0.2);
        padding: 15px;
        border-radius: 10px;
        border: 1px solid #cccccc;
        bottom: 12px;
        left: -50px;
        min-width: 280px;
      }
      .ol-popup:after, .ol-popup:before {
        top: 100%;
        border: solid transparent;
        content: " ";
        height: 0;
        width: 0;
        position: absolute;
        pointer-events: none;
      }
      .ol-popup:after {
        border-top-color: white;
        border-width: 10px;
        left: 48px;
        margin-left: -10px;
      }
      .ol-popup:before {
        border-top-color: #cccccc;
        border-width: 11px;
        left: 48px;
        margin-left: -11px;
      }
      .ol-popup-closer {
        text-decoration: none;
        position: absolute;
        top: 2px;
        right: 8px;
      }
      .ol-popup-closer:after {
        content: "✖";
      }
   
</style>

const { defineComponent, ref } = Vue;
<script>
import * as olInteraction from "ol/interaction";
import Map from "ol/Map";
import TileLayer from "ol/layer/Tile";
import View from "ol/View";
import OSM from "ol/source/OSM";
import "ol/ol.css";
import * as condition from "ol/events/condition";
import { fromLonLat } from "ol/proj";
import Overlay from "ol/Overlay";
import VectorLayer from "ol/layer/Vector";
import Feature from "ol/Feature";
import Point from "ol/geom/Point";
import VectorSource from 'ol/source/Vector';
import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue"
   import { defineComponent, ref } from 'vue'
export default defineComponent ({
  setup() {
    return {
      input: ref('')
    }
  },
   data() {
      
      return {
       zaTabelu: this.$store.state.APApoteke.sveApoteke,
      }
    },
    async mounted(){
      
      await this.$store.dispatch("APApoteke/dobaviApotekePacijenta")
      this.zaTabelu =this.$store.state.APApoteke.sveApoteke;
      


      this.map = new Map({
      target: "map",
      layers: [
        new TileLayer({
          source: new OSM(),
        }),
      ],
      view: new View({
        center: fromLonLat([19.83, 45.26]),
        zoom: 13,
      }),
    });
    let layer = null;
    let container = this.$refs["container"];
    let content = this.$refs["content"];
    let closer = this.$refs["closer"];
    let overlay = new Overlay({
      element: container,
      autoPan: true,
      autoPanAnimation: {
        duration: 250,
      },
    });
    let selectClick = new olInteraction.Select({
      condition: condition.pointerMove,
    });
    this.map.addInteraction(selectClick);
    this.map.addOverlay(overlay);

    closer.onclick = function () {
      overlay.setPosition(undefined);
      closer.blur();
      return false;
    };

    selectClick.on("select", function (evt) {
      try {
        let featureSelected = evt.selected[0];
        let layer = selectClick.getLayer(featureSelected);
        let ime = layer.get("ime");
        let ulica = layer.get("ulica")
        let broj = layer.get("broj")
        if (ime === undefined) return;
        content.innerHTML = `<p><b>${ime}</b></p>`;
        content.innerHTML += `<p>Ulica:${ulica}</p>`;
        content.innerHTML += `<p>Broj:${broj}</p>`;
        overlay.setPosition(evt.mapBrowserEvent.coordinate);
      } catch (Exception) {
        return;
      }
    });
    let apoteke = []
    this.$store.dispatch("APApoteke/dobaviApoteke").then(()=>{
      apoteke = this.$store.state.APApoteke.sveApoteke;
      apoteke.forEach((apoteka) => {
      layer = new VectorLayer({
        ime: apoteka.ime,
        ulica: apoteka.ulica,
        broj : apoteka.broj,
        
        source: new VectorSource({
          features: [
            new Feature({
              geometry: new Point(
                fromLonLat([apoteka.duzina,apoteka.sirina])
              ),
            }),
          ],
        }),
      });

      this.map.addLayer(layer);
    });
    });



    },
    name: 'APPostojeceApoteke',
    components:{
       NavMeniZaPacijenta
  },
    methods: {
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      open1() {
        this.$message({
          showClose: true,
          message: 'Uspjesno izvrsena pretplata na apoteku.',
          type: 'success'
        })}
      ,
      open2() {
        this.$message({
          showClose: true,
          message: 'Doslo je do greske',
          type: 'error'
        })}
      ,
      pretplata(index, row){
        console.log(row.id);
        row.pretplacen = true;
        this.$store.dispatch("APApoteke/pretplataNaApoteku", row.id)
        .then(response => {
          if (response.data){
            this.open1();
          } else{
            this.open2();
          }
        });
      },
     pretrazi() {
       //ovde ili instalirati onu glupost, koja nzm cemu sluzi, ili poslati beku
       //https://www.npmjs.com/package/vue-input-search
       //https://vuejs.org/v2/guide/forms.html
       //bdw ako stavim : bez () mogu da unesem tekst, ako stavim samo () ne moze ha ha;
        
        if(this.input===""){this.zaTabelu = this.$store.state.APApoteke.sveApoteke;}
        this.zaTabelu = this.$store.state.APApoteke.sveApoteke.filter( (item) => {return (item.ime.includes( this.input) 
        || item.adresa.ulica.includes(this.input)  || item.adresa.Mesto.includes(this.input) || item.adresa.broj.includes(this.input))} );
     },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      }
    }
  })

</script>
