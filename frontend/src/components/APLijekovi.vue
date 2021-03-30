<template>
  
    
    <AANavMeni/>
    
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
      
    }
  },
  mounted(){
      //pozivanje ucitavanja podataka lijekova
      this.$store.dispatch("APlijekovi/dobaviLijekove")
     
      
    },
  components:{
      AANavMeni,
      AASviLijekovi
      
  }

}
</script>
