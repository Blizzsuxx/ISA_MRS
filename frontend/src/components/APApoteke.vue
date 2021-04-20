<template>
  
    <APPostojeceApoteke/>
    
</template>

<script>
import APPostojeceApoteke from './APPostojeceApoteke'
export default {
  name: 'APApoteke',
   mounted(){
      //pozivanje ucitavanja podataka apoteka
       this.$store.dispatch("APApoteke/dobaviApoteke")
      
    },
  components:{
      APPostojeceApoteke
  }

}
</script>
<style scoped>
body{
background-color: "#4099FF";
    text-color: "#fff";
    active-text-color: "#D6E3F1";}
</style>
