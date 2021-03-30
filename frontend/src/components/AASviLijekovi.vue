<template>
<el-alert
    v-if="greska"
    :data="lijekovi"
    title="Greska pri prebacivanju"
    type="error" center>{{poruka}}
  </el-alert>
  <h3>Pregled svih lijekova</h3> 
  
  <LijekoviTabela @promjenjena-selekcija="selektujRedove" ref='dijete' v-bind:lijekovi="$store.state.APlijekovi.sviLijekovi" referenca="multipleTable" />
  <div style="margin-top: 20px">
    <el-button type="primary" @click="ocistiSelekciju()" plain>Očisti selekciju</el-button>
    <el-button type="primary" @click="promjeniStanje()" plain>Promjeni stanje</el-button>
    
  </div>
</template>  
 

<script>
import LijekoviTabela from  './LijekoviTabela'
export default {
  name: 'APMagacinLijekovi',
  data() {
      return {
        greska : false,
        poruka : ""
      }
    },
    components:{
      LijekoviTabela
    },
    methods: {
      ocistiSelekciju(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.dijete.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.dijete.$refs.multipleTable.clearSelection();
        }
      },
      promjeniStanje(){
        this.$store.dispatch("APlijekovi/promjeniStanje",this.$refs.dijete.multipleSelection)
        if(this.$store.state.APlijekovi.zabranjeni.length!=0){
        this.greska=true;
        this.poruka = `Lijekovi sa identifikatorima: ${this.$store.state.APlijekovi.zabranjeni.join(',')} ne mogu biti prebaceni u magacin jer
         se nalaze u nekoj od rezervacija pacijenata`;
        }
        else{
          this.greaska = false;
          
        }
        this.$refs.dijete.$refs.multipleTable.clearSelection();
      },
      selektujRedove(val) {
        this.$refs.dijete.multipleSelection = val;
      }
    }
  }

</script>