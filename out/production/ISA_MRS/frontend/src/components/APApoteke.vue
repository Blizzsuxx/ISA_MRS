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
