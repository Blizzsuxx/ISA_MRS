<template>
<el-main>
    <navMeniPocetni/>
<APPostojeceApoteke/>
  </el-main>
</template>

<script>
import APPostojeceApoteke from "./APPostojeceApoteke"
import NavMeniPocetni from "./NavMeniPocetni"
 export default {
    
  components: { APPostojeceApoteke, NavMeniPocetni},
    name: 'IzmenaLicnihInfoKorisnika'};
</script>