<template>
  
    
    <navMeniPocetni/>
    
    <el-row :gutter="20">
    <el-col :span="24"><LijekoviTabela @promjenjena-selekcija="selektujRedove" ref='dijete' v-bind:lijekovi="$store.state.APlijekovi.sviLijekovi" referenca="multipleTable" /></el-col>
    </el-row>
</template>

<script>

import LijekoviTabela from './LijekoviTabela'

import NavMeniPocetni from './NavMeniPocetni.vue'
export default {
  name: 'LekoviNeautentifikovanKorisnik',
  data(){
    return {
      
    }
  },
  mounted(){
      //pozivanje ucitavanja podataka lijekova
      this.$store.dispatch("APlijekovi/dobaviLijekoveN")
     
      
    },
  components:{
     
      LijekoviTabela,
    NavMeniPocetni
      
  }

}
</script>
