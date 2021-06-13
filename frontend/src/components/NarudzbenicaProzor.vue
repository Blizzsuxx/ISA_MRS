<template>
  <NavAdminApoteke />
  <el-row :gutter="20" v-if="loaded">
    <el-col :span="8">
      Rok: {{narudzbenica.rok}}
      </el-col>
    <el-col :span="8">
      Zavrsena: {{zavrsena}}
      </el-col>
    <el-col :span="8">
      Ukupan broj ponuda: {{narudzbenica.brojPonuda}}
      </el-col>
    </el-row>
   <el-row :gutter="20">
     <PonudeTabela :ponude="$store.state.Ponude.svePonude" :narudzbenica="narudzbenica" @odobrena-ponuda="odobrenaPonuda"></PonudeTabela>
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
import PonudeTabela from "./PonudeTabela"
export default {
  name: "NarudzbenicaProzor",
  data() {
    return {
      id : this.$route.params.id,
      narudzbenica: {},
      loaded:false,
    }
  },
  components: {
    NavAdminApoteke,
    PonudeTabela
  },
  computed:{
    zavrsena(){
      if(!this.loaded)
        return ""
      if(!this.narudzbenica.zavrsena){
        return "Nije zavrsena"
      }
      else{
        return "Zavrsena"
      }
    }
  },
  mounted() {
    this.$store.dispatch("Narudzbenice/dobaviNarudzbenicu",this.id).then(()=>{
        this.narudzbenica =  this.$store.state.Narudzbenice.narudzbenica;
        this.loaded = true;
        
    })
    this.$store.dispatch("Ponude/dobaviPonude",this.id);
  },
  methods:{
    odobrenaPonuda(){
      this.narudzbenica.prihvacena=true;
    }
  }
};
</script>