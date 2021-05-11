<template>
  <NavAdminApoteke />
  <el-row :gutter="20">
    <el-col :span="24">
      <h5>Dermatolozi</h5>
      <GodisnjiOdmoriTabela
        @promjenjena-selekcija="promjenaGodisnjegOdmora"
        v-bind:godisnjiOdmori="$store.state.GodisnjiOdmori.sviOdmori"
    /></el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="24">
    <el-popconfirm title="Da li ste sigurni da zelite da otpustitite farmaceuta?" @confirm="potvrdjeno" confirmButtonText='OK'
  cancelButtonText='Odustani'>
      <template #reference>
        <el-button type="danger" plain>Delete</el-button>
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
      godisnjiOdmor : null
    }
  },
  methods: {
    promjenaDermatologa (val) {
      this.godisnjiOdmor = val
    },
    potvrdjeno(){
       this.$store.dispatch("Farmaceuti/otpustiFarmaceuta",this.dermatolog.id).then(()=>{
           this.$store.dispatch("Farmaceuti/dobaviFarmaceuteAdmin");
       })
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