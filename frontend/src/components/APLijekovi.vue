<template>
  
    <APTrenutniLijekovi/>
    <APMagacinLijekovi/>
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
      APMagacinLijekovi
  }

}
</script>
