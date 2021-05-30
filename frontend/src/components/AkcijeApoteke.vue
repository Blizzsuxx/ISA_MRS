<template>
<el-main>
 <NavMeniZaPacijenta/>

 <el-row>
  <el-col :span="6" v-for="(o, index) in broj" :key="o" :offset="index > 0 ? broj : 1">
    <el-card :body-style="{ padding: '0px' }">
      <img src="../assets/eksn.png" class="image">
      <div style="padding: 14px;">
        <span>{{lista[0].opis}}</span>
        <div class="bottom">
          <time class="time">{{ currentDate }}</time>
         
        </div>
      </div>
    </el-card>
  </el-col>
</el-row>
</el-main>
</template>

<style>
  .time {
    font-size: 13px;
    color: #999;
  }
  
  .bottom {
    margin-top: 13px;
    line-height: 12px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .button {
    padding: 0;
    min-height: auto;
  }

  .image {
    width: 60%;
    display: block;
  }
</style>


<script>
import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue"
export default  {
 components :{NavMeniZaPacijenta},
 name: 'AkcijeApoteke',
 data() {
    return {
      currentDate: new Date(),
      lista: [{opis: "super akcija kreme za ruke", stanjeLijeka: null}],
      broj: 0
      
    };
  },
  async mounted(){ //ovde ima greska prikazuju se od 1 i 2, bez 0??
      
      //await this.$store.dispatch("APAkcije/dobaviAkcije")
      console.log(this.lista);
      this.broj=this.$store.state.APAkcije.akcije.length-1
      console.log(this.broj)
      this.lista =this.$store.state.APAkcije.akcije;
      console.log(this.lista)
      
    }
    
};
</script>
