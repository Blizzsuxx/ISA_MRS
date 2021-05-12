<template>
  <NavAdminApoteke />
  <el-alert
    v-if="greska"
    @close="greska=false"
    title="Greska"
    type="error" center>{{poruka}}
  </el-alert>
  <el-row :gutter="20">
    <el-col :span="24">
      <h5>Zahtjevi za godisnje odmore radnika</h5>
      <GodisnjiOdmoriTabela
        @promjenjena-selekcija="promjenaGodisnjegOdmora"
        v-bind:godisnjiOdmori="$store.state.GodisnjiOdmori.sviOdmori"
    /></el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="24">
        <el-popconfirm title="Da li ste sigurni da zelite da prihvatite zahtjev za godisnji odmor?" @confirm="potvrdjeno" confirmButtonText='Potvrdi'
  cancelButtonText='Odustani'>
      <template #reference>
        <el-button plain>Odobri zahtjev</el-button>
      </template>
    </el-popconfirm>
    <el-popconfirm title="Da li ste sigurni da zelite da odbijete zahtjev za godisnji odmor?" @confirm="odbijeno" confirmButtonText='OK'
  cancelButtonText='Odustani'>
      <template #reference>
        <el-button type="danger" plain>Odbij zahtjev</el-button>
      </template>
    </el-popconfirm>
    </el-col>
  </el-row>
</template>
<style scoped>
.item {
  margin-bottom: 18px;
}

.box-card {
  width: 600px;
}
.poravnanje {
  align-content: center;
  align-items: center;
  text-align: center;
}
#app {
  position: relative;

  display: flex;
  justify-content: center;
  align-items: center;
}
</style>

<script>
import NavAdminApoteke from "./NavAdminApoteke";
import GodisnjiOdmoriTabela from "./GodisnjiOdmoriTabela";
export default {
  name: "PrihvatanjeGodisnjihOdmora",
  props: { options: String },
  data() {
    return {
      godisnjiOdmor : null,
      greska : false,
      poruka : "",
    }
  },
  methods: {
    promjenaGodisnjegOdmora (val) {
      this.godisnjiOdmor = val
    },
    odbijeno(){
      if(this.godisnjiOdmor.odobren===null){
       this.$store.dispatch("GodisnjiOdmori/odbijGodisnjiOdmor",this.godisnjiOdmor.id).then(()=>{
           this.$store.dispatch("GodisnjiOdmori/godisnjiOdmoriAdmin");
       })
      }
      else{
        this.poruka = "Zahtjev moze biti obrađen samo jednom."
        this.greska = true;
      }
    
    },
    potvrdjeno(){
      if(this.godisnjiOdmor.odobren===null){
       this.$store.dispatch("GodisnjiOdmori/odobriGodisnjiOdmor",this.godisnjiOdmor.id).then(()=>{
           this.$store.dispatch("GodisnjiOdmori/godisnjiOdmoriAdmin");
       })
      }
      else{
        this.poruka = "Zahtjev moze biti obrađen samo jednom."
        this.greska = true;
      }
    }
  },
  components: {
    NavAdminApoteke,
    GodisnjiOdmoriTabela,
  },
  mounted() {
    this.$store.dispatch("GodisnjiOdmori/godisnjiOdmoriAdmin");
  },

  
};
</script>