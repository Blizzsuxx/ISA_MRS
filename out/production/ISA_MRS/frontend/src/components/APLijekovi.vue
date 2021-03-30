<template>
  
    
    <AANavMeni/>
    <el-alert
    v-if="greska"
    title="error alert"
    type="error">{{poruka}}
  </el-alert>
    <el-row :gutter="20">
    <el-col :span="24"><AASviLijekovi/></el-col>
    </el-row>
</template>

<script>

import AASviLijekovi from './AASviLijekovi'
import AANavMeni from './AANavMeni'
export default {
  name: 'APLijekovi',
  data(){
    return {
      greska : false,
      poruka : ""
    }
  },
  mounted(){
      //pozivanje ucitavanja podataka lijekova
      this.$store.dispatch("APlijekovi/dobaviLijekove")
      console.log(this.$store)
      if(this.$store.state.APlijekovi.zabranjeni.length!=0){
        this.greska=true;
        this.poruka = `Lijekovi sa identifikatorima: ${this.$store.state.APlijekovi.zabranjeni.join(',')} ne mogu biti prebaceni u magacin jer
         se nalaze u nekoj od rezervacija pacijenata`;
      }
    },
  components:{
      AANavMeni,
      AASviLijekovi
      
  }

}
</script>
