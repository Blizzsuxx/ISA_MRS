<template>
  <NavAdminApoteke />
  <el-row :gutter="20">
    <el-col :span="24">
      <h5>Istorija narudzbenica</h5>
      <NarudzbeniceTabela
        v-bind:narudzbenice="$store.state.Farmaceuti.sviFarmaceuti"
    /></el-col>
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
import NarudbzbeniceTabela from "./NarudzbeniceTabela";
export default {
  name: "PrikazNarudzbenica",
  props: { options: String },
  data() {
    return {
      farmaceut : null
    }
  },
  methods: {
    promjeniFarmaceuta (val) {
      this.farmaceut = val
    },
    potvrdjeno(){
     
       this.$store.dispatch("Farmaceuti/zaposliFarmaceuta",this.farmaceut.id).then(()=>{
           this.$store.dispatch("Farmaceuti/dobaviNezaposleneFarmaceuteAdmin");
       })
    }
  },
  components: {
    NavAdminApoteke,
    NarudbzbeniceTabela,
  },
  mounted() {
    this.$store.dispatch("Farmaceuti/dobaviNezaposleneFarmaceuteAdmin");
  },
};
</script>