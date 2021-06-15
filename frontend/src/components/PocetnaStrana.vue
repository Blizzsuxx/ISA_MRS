<template>
<el-main>
    <navMeniPocetni/>
<ApotekeSve/>
  </el-main>
</template>

<script>
import ApotekeSve from "./ApotekeSve"
import NavMeniPocetni from "./NavMeniPocetni"
 export default {
    
  components: { ApotekeSve, NavMeniPocetni},
    name: 'IzmenaLicnihInfoKorisnika'};
</script>