<template>
<NavAdminApoteke />
 <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="Prosječne ocjene" name="first">
        <Bar ref="ocjene" />



    </el-tab-pane>
    <el-tab-pane label="Pregledi" name="second">
       <PreglediBar ref="pregledi" />

    </el-tab-pane>

    <el-tab-pane label="Lijekovi" name="third">
       <LijekoviBar ref="lijekovi" />

    </el-tab-pane>

    <el-tab-pane label="Prihodi" name="fourth">
       <Period ref="prihodi" />

    </el-tab-pane>
  </el-tabs>
</template>  
 

<script>
import NavAdminApoteke from "./NavAdminApoteke"
import Bar from "./grafici/Bar"
import LijekoviBar from "./grafici/LijekoviBar"
import PreglediBar from "./grafici/PreglediBar"
import Period from "./grafici/Period"
export default {
  name: 'Izvjestaji',
  data() {
      return {
       activeName: 'first',
       podaci : null,
      }
    },
    components:{
      NavAdminApoteke,
      Bar,
      PreglediBar,
      LijekoviBar,
      Period
     
    },
    mounted(){
      this.$store.dispatch("APApoteke/izvjestaj").then((podaci)=>{
          this.podaci = podaci;
          this.formirajIzvjestaje();
      })
    },
    
  

    methods: {
         handleClick(tab, event) {
             console.log(tab, event);
         },
         formirajIzvjestaje(){
           this.$refs.ocjene.iscrtaj(this.podaci)
           this.$refs.pregledi.iscrtaj(this.podaci)
           this.$refs.lijekovi.iscrtaj(this.podaci)
         }
    }
  }

</script>