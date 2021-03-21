<template>
  
    <APTrenutniLijekovi/>
    <APMagacinLijekovi/>
</template>

<script>
import APTrenutniLijekovi from './APTrenutniLijekovi'
import APMagacinLijekovi from './APMagacinLijekovi'
export default {
  name: 'APLijekovi',
  components:{
      APTrenutniLijekovi,
      APMagacinLijekovi
  }
}
</script>
