<template>
  
    
    
    <el-row :gutter="20">
  <el-col :span="12"><APTrenutniLijekovi/></el-col>
  <el-col :span="12"><APMagacinLijekovi/></el-col>
  
    </el-row>
</template>

<script>
import APTrenutniLijekovi from './APTrenutniLijekovi'
import APMagacinLijekovi from './APMagacinLijekovi'
export default {
  name: 'APLijekovi',
  mounted(){
      //pozivanje ucitavanja podataka lijekova
      this.$store.dispatch("APlijekovi/dobaviLijekove")
      
    },
  components:{
      APTrenutniLijekovi,
      APMagacinLijekovi,
      
  }

}
</script>
