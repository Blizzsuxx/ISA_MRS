<template>
  
    <DTOLijekoviTabela ref="dijete" v-bind:lijekovi="$store.state.APlijekovi.dtoLijekovi"/>
    
</template>

<script>
import DTOLijekoviTabela from './DTOLijekoviTabela'
export default {
  name: 'ASLijekovi',
  data() {
    return {
      
    }
  },
  mounted(){
      this.$store.dispatch('APlijekovi/dobaviDTOLijekove')
      .then(response => {
        return response;
      })
    },
  components:{
      DTOLijekoviTabela

  }
}
</script>