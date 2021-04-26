<template>
<el-main>
<NavMeniZaPacijenta/>
  <APPostojeceApoteke/>
  </el-main>
</template>

<script>
import APPostojeceApoteke from "./APPostojeceApoteke"
import NavMeniZaPacijenta from "./NavMeniZaPacijenta"
 export default {
    
  components: { APPostojeceApoteke, NavMeniZaPacijenta},
    name: 'IzmenaLicnihInfoKorisnika'};
</script>