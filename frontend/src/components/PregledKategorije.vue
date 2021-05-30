<template>
    <NavMeniZaPacijenta/>
   
    <el-card class="box-card" >
  <div class="box-card" v-if="this.gold==1">
    {{this.tekst}}
  </div>
  <div  class="box-card2" v-if="this.gold==2">

{{this.tekst}}
  </div>
  <div  class="box-card3" v-if="this.gold==3">

{{this.tekst}}
  </div>
</el-card>


</template>
<script>
import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue"
export default  {
 components :{NavMeniZaPacijenta},
 data() { 
        return {
          tekst: "",
          gold: 1
         
        }
    },
 name: 'PregledKategorija',
    async mounted(){
     this.$store.dispatch("APPacijenti/dobaviKategorijuPacijenta").then(()=>{
        this.tekst=this.$store.state.APPacijenti.kategorija;
        
        if(this.tekst.includes("GOLD")){this.gold=1;}
        else if(this.tekst.includes("REGULAR")){this.gold=3;}
        else {this.gold=2;}
        console.log(this.gold)
     })
    
    },
};
</script>
<style>
  

  .box-card {
   
    background-color: #f5db53;
  }
   .box-card2 {
   
    background-color: #b9b59e;
  }
   .box-card3 {
   
    background-color: #409EFF;
  }
</style>