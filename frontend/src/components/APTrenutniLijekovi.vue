<template>
  <h2>Lekovi koji su u magacinu</h2> 
  <LijekoviTabela @promjenjena-selekcija="selektujRedove" ref='dijete1' v-bind:lijekovi="$store.state.APlijekovi.lijekoviProdaja" referenca="multipleTable" />
  <div style="margin-top: 20px">
    <el-button @click="ocistiSelekciju()">Očisti selekciju</el-button>
    <el-button @click="izbaciIzProdaje()">Premesti u magacin</el-button>
  </div>
</template>  ref="multipleTable"
 

<script>
import LijekoviTabela from  './LijekoviTabela'
export default {
  name: 'APTrenutniLijekovi',
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
            this.$refs.dijete1.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.dijete1.$refs.multipleTable.clearSelection();
        }
      },
      izbaciIzProdaje(){
        this.$store.dispatch("APlijekovi/izbaciIzProizvodnje",this.$refs.dijete1.multipleSelection)
        this.$refs.dijete1.$refs.multipleTable.clearSelection();
      },
      selektujRedove(val) {
        this.$refs.dijete1.multipleSelection = val;
      }
    }
  }

</script>
