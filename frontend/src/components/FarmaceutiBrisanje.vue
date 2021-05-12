<template>
  <NavAdminApoteke />
  <el-row :gutter="20">
    <el-col :span="24">
      <h5>Farmaceuti</h5>
      <DermatoloziTabela
        @promjenjena-selekcija="promjenaDermatologa"
        v-bind:dermatolozi="$store.state.Farmaceuti.sviFarmaceuti"
    /></el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="24">
    <el-popconfirm title="Da li ste sigurni da zelite da otpustitite farmaceuta?" @confirm="potvrdjeno" confirmButtonText='OK'
  cancelButtonText='Odustani'>
      <template #reference>
        <el-button type="danger" plain>Obrisi farmaceuta</el-button>
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
import DermatoloziTabela from "./DermatoloziTabela";
import { mapState } from "vuex";
export default {
  name: "FarmaceutiBrisanje",
  props: { options: String },
  data() {
    return {
      dermatolog : null
    }
  },
  methods: {
    promjenaDermatologa (val) {
     
      this.dermatolog = val
      
    },
    potvrdjeno(){
     
       this.$store.dispatch("Farmaceuti/otpustiFarmaceuta",this.dermatolog.id).then(()=>{
           this.$store.dispatch("Farmaceuti/dobaviFarmaceuteAdmin");
       })
    }
  },
  components: {
    NavAdminApoteke,
    DermatoloziTabela,
  },
  mounted() {
    this.$store.dispatch("Farmaceuti/dobaviFarmaceuteAdmin");
  },

  computed: {
    ...mapState("APApoteke", ["apoteka"]),
  },
};
</script>