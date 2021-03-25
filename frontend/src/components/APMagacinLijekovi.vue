<template>
  <h2>Lekovi koji su u magacinu</h2> 
  <LijekoviTabela @promjenjena-selekcija="selektujRedove" ref='dijete' v-bind:lijekovi="$store.state.APlijekovi.lijekoviMagacin" referenca="multipleTable" />
  <div style="margin-top: 20px">
    <el-button @click="ocistiSelekciju()">Očisti selekciju</el-button>
    <el-button @click="ubaciUProizvodnju()">Premesti u prodaju</el-button>
  </div>
</template>  ref="multipleTable"
 

<script>
import LijekoviTabela from  './LijekoviTabela'
export default {
  name: 'APMagacinLijekovi',
  data() {
      return {
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
      ubaciUProizvodnju(){
        this.$store.dispatch("APlijekovi/ubaciUProizvodnju",this.$refs.dijete.multipleSelection)
        this.$refs.dijete.$refs.multipleTable.clearSelection();
      },
      selektujRedove(val) {
        this.$refs.dijete.multipleSelection = val;
      }
    }
  }

</script>
