<template>
    
    <APPostojeceApoteke/>
    
</template>

<script>
import APPostojeceApoteke from './APPostojeceApoteke'
export default {
  name: 'APApoteke',
  async mounted(){
      //pozivanje ucitavanja podataka apoteka
      await this.$store.dispatch("APApoteke/dobaviApoteke")
      this.tableData =this.$store.state.APApoteke.apoteke;
    },
  components:{
      APPostojeceApoteke
  }

}
</script>
