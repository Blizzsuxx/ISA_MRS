<template>
  
    
    <NavAdminApoteke/>
    
    <el-row :gutter="20">
    <el-col :span="24"><AASviLijekovi/></el-col>
    </el-row>
</template>

<script>

import AASviLijekovi from './AASviLijekovi'
import NavAdminApoteke from './NavAdminApoteke'
export default {
  name: 'APLijekovi',
  data(){
    return {
      
    }
  },
  mounted(){
      //pozivanje ucitavanja podataka lijekova
      this.$store.dispatch("APlijekovi/dobaviLijekoveAdmin")
     
      
    },
  components:{
      NavAdminApoteke,
      AASviLijekovi
      
  }

}
</script>
