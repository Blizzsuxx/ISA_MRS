<template>
  <NavAdminApoteke />
  <el-row :gutter="20">
    <el-col :span="24">
      <h5>Farmaceuti</h5>
      <DermatoloziTabela
        @promjenjena-selekcija="promjeniFarmaceuta"
        v-bind:dermatolozi="$store.state.Farmaceuti.sviFarmaceuti"
    /></el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="24">
    <el-popconfirm title="Da li ste sigurni da želite da zaposlite farmaceuta?" @confirm="potvrdjeno" confirmButtonText='Potvrdi'
  cancelButtonText='Odustani'>
      <template #reference>
        <el-button type="primary" plain>Zaposli farmaceuta</el-button>
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
  name: "FarmaceutZaposljavanje",
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
    DermatoloziTabela,
  },
  mounted() {
    this.$store.dispatch("Farmaceuti/dobaviNezaposleneFarmaceuteAdmin");
  },

  computed: {
    ...mapState("APApoteke", ["apoteka"]),
  },
};
</script>