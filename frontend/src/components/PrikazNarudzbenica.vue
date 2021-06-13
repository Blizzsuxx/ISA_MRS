<template>
  <NavAdminApoteke />
  <el-row :gutter="20">
    <el-col :span="24">
      <h5>Istorija narudzbenica</h5>
      <NarudzbeniceTabela
        v-bind:narudzbenice="$store.state.Narudzbenice.sveNarudzbenice"
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
import NarudzbeniceTabela from "./NarudzbeniceTabela";
export default {
  name: "PrikazNarudzbenica",
  data() {
    return {
    }
  },
  components: {
    NavAdminApoteke,
    NarudzbeniceTabela,
  },
  mounted() {
    this.$store.dispatch("Narudzbenice/dobaviNarudzbenice");
  },
};
</script>