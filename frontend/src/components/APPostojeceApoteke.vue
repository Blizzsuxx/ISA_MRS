<template>
  <NavMeniZaPacijenta />
  <div id="map" class="map">
    <div id="popup" class="ol-popup" ref="container">
      <a href="#" id="popup-closer" class="ol-popup-closer" ref="closer"></a>
      <div id="popup-content" ref="content"></div>
    </div>
  </div>
  <el-input
    placeholder="Radijus u kilometrima"
    v-model="circleRadius"
    v-on:keyup="prilagodiKrug"
  ></el-input>
  <el-main>
    <h2>Sve registrovane apoteke</h2>
    <div style="margin-top: 20px">
      <!-- <el-button @click="toggleSelection([tableData[1], tableData[2]])">Toggle selection status of second and third rows</el-button> -->
      <el-input placeholder="Search" v-model="input"></el-input>
      <button v-on:click="pretrazi">Pretrazi</button>
    </div>
    <el-table ref="zaTabelu" :data="zaTabelu" style="width: 100%">
      <el-table-column property="id" label="ID" width="120">
        <template #default="scope">
          <router-link
            tag="button"
            :to="{
              name: 'ProfilApotekeKorisnik',
              params: { id: scope.row.id },
            }"
          >
            {{ scope.row.id }}
          </router-link>
        </template>
      </el-table-column>
      <el-table-column  sortable property="ime" label="Naziv" width="120">
      </el-table-column>
      <el-table-column  sortable property="mjesto" label="Mesto" show-overflow-tooltip>
      </el-table-column>
      <el-table-column  sortable property="ulica" label="Ulica" show-overflow-tooltip>
      </el-table-column>
      <el-table-column  sortable property="broj" label="Broj" show-overflow-tooltip>
      </el-table-column>
      <el-table-column  sortable property="ocena" label="Ocena" show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        property="udaljenost"
        label="Udaljenost"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column align="right">
        <template #default="scope">
          <el-popconfirm
            confirmButtonText="Da"
            cancelButtonText="Ne"
            icon="el-icon-info"
            iconColor="red"
            title="Zelite da se pretplatite na izabranu apoteku?"
            @confirm="pretplata(scope.$index, scope.row)"
          >
            <template #reference>
              <el-button
                size="mini"
                type="danger"
                :disabled="scope.row.pretplacen"
                >{{
                  scope.row.pretplacen ? "Pretplacen" : "Pretplata"
                }}</el-button
              >
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
  height: 400px;
}
.ol-popup {
  position: absolute;
  background-color: white;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.2);
  padding: 15px;
  border-radius: 10px;
  border: 1px solid #cccccc;
  bottom: 12px;
  left: -50px;
  min-width: 280px;
}
.ol-popup:after,
.ol-popup:before {
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
import { toLonLat } from "ol/proj";
import "ol/ol.css";
import * as condition from "ol/events/condition";
import { fromLonLat } from "ol/proj";
import Overlay from "ol/Overlay";
import VectorLayer from "ol/layer/Vector";
import Feature from "ol/Feature";
import { Circle as CircleStyle } from "ol/style";
import Point from "ol/geom/Point";
import Geolocation from "ol/Geolocation";
import VectorSource from "ol/source/Vector";
import Circle from "ol/geom/Circle";
import Style from "ol/style/Style";
import Stroke from "ol/style/Stroke";
import Fill from "ol/style/Fill";
import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue";
//import {get as getProjection} from 'ol/proj';
import { getDistance } from "ol/sphere";
import { defineComponent, ref } from "vue";
export default defineComponent({
  setup() {
    return {
      input: ref(""),
    };
  },
  data() {
    return {
      zaTabelu: this.$store.state.APApoteke.sveApoteke,
      lon: 0,
      lat: 0,
      startingPoint: null,
      circleRadius: null,
      circleLayer: null,
      emptyRadius: true,
      geolokacija: false,
    };
  },
  async mounted() {
    await this.$store.dispatch("APApoteke/dobaviApotekePacijenta");
    this.zaTabelu = this.$store.state.APApoteke.sveApoteke;
    this.view = new View({
      center: fromLonLat([19.83, 45.26]),
      zoom: 13,
    });
    this.map = new Map({
      target: "map",
      layers: [
        new TileLayer({
          source: new OSM(),
        }),
      ],
      view: this.view,
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
        let ulica = layer.get("ulica");
        let broj = layer.get("broj");
        if (ime === undefined) return;
        content.innerHTML = `<p><b>${ime}</b></p>`;
        content.innerHTML += `<p>Ulica:${ulica}</p>`;
        content.innerHTML += `<p>Broj:${broj}</p>`;
        overlay.setPosition(evt.mapBrowserEvent.coordinate);
      } catch (Exception) {
        return;
      }
    });
    let vm = this;
    this.map.on("singleclick", function (evt) {
      if (overlay.getPosition() !== undefined) {
        overlay.setPosition(undefined);

        return;
      }
      this.getLayers().forEach((element) => {
        if (element === vm.startingPoint) {
          this.removeLayer(element);
        }
      });

      let coordinate = toLonLat(evt.coordinate, "EPSG:3857");
      vm.lon = coordinate[0];
      vm.lat = coordinate[1];
      layer = new VectorLayer({
        source: new VectorSource({
          features: [
            new Feature({
              geometry: new Point(fromLonLat([coordinate[0], coordinate[1]])),
            }),
          ],
        }),
      });
      vm.startingPoint = layer;
      this.addLayer(vm.startingPoint);
    });

    var geolocation = new Geolocation({
      // enableHighAccuracy must be set to true to have the heading value.
      trackingOptions: {
        enableHighAccuracy: true,
      },
      projection: this.view.getProjection(),
    });

    geolocation.setTracking(this.geolokacija);

    var accuracyFeature = new Feature();
    geolocation.on("change:accuracyGeometry", function () {
      accuracyFeature.setGeometry(geolocation.getAccuracyGeometry());
    });

    var positionFeature = new Feature();
    positionFeature.setStyle(
      new Style({
        image: new CircleStyle({
          radius: 6,
          fill: new Fill({
            color: "#3399CC",
          }),
          stroke: new Stroke({
            color: "#fff",
            width: 2,
          }),
        }),
      })
    );

    geolocation.on("change:position", function () {
      var coordinates = geolocation.getPosition();
      positionFeature.setGeometry(coordinates ? new Point(coordinates) : null);
    });

    geolocation.on("error", function (error) {
      console.log(error.message);
    });

    let newLayer = new VectorLayer({
      source: new VectorSource({
        features: [accuracyFeature, positionFeature],
      }),
    });
    this.startingPoint = newLayer;
    this.map.addLayer(this.startingPoint);

    let apoteke = [];
    this.$store.dispatch("APApoteke/dobaviApoteke").then(() => {
      apoteke = this.$store.state.APApoteke.sveApoteke;
      apoteke.forEach((apoteka) => {
        layer = new VectorLayer({
          ime: apoteka.ime,
          ulica: apoteka.ulica,
          broj: apoteka.broj,

          source: new VectorSource({
            features: [
              new Feature({
                geometry: new Point(
                  fromLonLat([apoteka.duzina, apoteka.sirina])
                ),
              }),
            ],
          }),
        });

        this.map.addLayer(layer);
      });
    });
  },
  name: "APPostojeceApoteke",
  components: {
    NavMeniZaPacijenta,
  },
  methods: {
    promjena() {
      console.log(this.geolokacija);
    },
    toggleSelection(rows) {
      if (rows) {
        rows.forEach((row) => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    open1() {
      this.$message({
        showClose: true,
        message: "Uspjesno izvrsena pretplata na apoteku.",
        type: "success",
      });
    },
    open2() {
      this.$message({
        showClose: true,
        message: "Doslo je do greske",
        type: "error",
      });
    },
    pretplata(index, row) {
      console.log(row.id);
      row.pretplacen = true;
      this.$store
        .dispatch("APApoteke/pretplataNaApoteku", row.id)
        .then((response) => {
          if (response.data) {
            this.open1();
          } else {
            this.open2();
          }
        });
    },

    pretrazi() {
      //ovde ili instalirati onu glupost, koja nzm cemu sluzi, ili poslati beku
      //https://www.npmjs.com/package/vue-input-search
      //https://vuejs.org/v2/guide/forms.html
      //bdw ako stavim : bez () mogu da unesem tekst, ako stavim samo () ne moze ha ha;

      if (this.input === "") {
        this.zaTabelu = this.$store.state.APApoteke.sveApoteke;
      }
      this.zaTabelu = this.$store.state.APApoteke.sveApoteke.filter((item) => {
        {
          if (
            (item.ime.includes(this.input) ||
              item.adresa.ulica.includes(this.input) ||
              item.adresa.Mesto.includes(this.input) ||
              item.adresa.broj.includes(this.input)) &&
            this.distanceBetweenPoints([item.sirina, item.duzina])
          )
            return true;

          return false;
        }
      });
    },
    distanceBetweenPoints: function (latlng2) {
      if (this.emptyRadius) return true;
      let finalmeasure = getDistance([this.lat, this.lon], latlng2);
      if (parseInt(finalmeasure) < parseInt(this.circleRadius) * 1000) {
        return true;
      }
      return false;
    },
    prilagodiKrug() {
      this.map.getLayers().forEach((element) => {
        if (element === this.circleLayer) {
          this.map.removeLayer(element);
        }
      });

      if (this.circleRadius === "") {
        this.emptyRadius = true;
        return;
      } else {
        this.emptyRadius = false;
      }
      if (this.lon === null && this.emptyRadius === false) {
        alert("Izaberite tacku na mapi");
        return;
      }

      var centerLongitudeLatitude = fromLonLat([this.lon, this.lat]);
      this.circleLayer = new VectorLayer({
        source: new VectorSource({
          projection: "EPSG:4326",
          features: [
            new Feature(
              new Circle(
                centerLongitudeLatitude,
                parseInt(this.circleRadius) * 1000
              )
            ),
          ],
        }),
        style: [
          new Style({
            stroke: new Stroke({
              color: "blue",
              width: 3,
            }),
            fill: new Fill({
              color: "rgba(0, 0, 255, 0.1)",
            }),
          }),
        ],
      });
      this.map.addLayer(this.circleLayer);
      this.pretrazi();
    },

    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
  },
});
</script>
