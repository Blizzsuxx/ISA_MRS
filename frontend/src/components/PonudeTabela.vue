
<template>

  <el-table
    :data="ponude"
    style="width: 100%">
    <el-table-column
      prop="id"
      label="Sifra"
      >
    </el-table-column>
    <el-table-column
      prop="nazivPonude"
      label="Naziv ponude"
      >
    </el-table-column>
    <el-table-column
      prop="dobavljac"
      label="Dobavljac"
      >
    </el-table-column>
    <el-table-column
      prop="cijenaPonude"
      label="Cijena ponude"
      >
    </el-table-column>
    <el-table-column
      prop="status"
      label="Status"
      >
    </el-table-column>
    <el-table-column
      fixed="right"
      label="Opcije"
     >
      <template #default="scope">
        <el-button type="primary" plain v-if="scope.row.status===0 && narudzbenica.zavrsena && ! narudzbenica.prihvacena && narudzbenica.pripada" @click="()=>prihvatiPonudu(scope.row.id)"  size="small">Potvrdi</el-button>
        <div v-if="scope.row.status===2">
            Odobrena
            </div>
        <div v-if="scope.row.status===1">
            Odbijena
            </div>
      </template>
    </el-table-column>
  </el-table>

</template>

<script>
  export default {
      data() {
      return {
        nId : this.$route.params.id,
      }
    },
    methods: {
      prihvatiPonudu(id){
          this.$store.dispatch("Ponude/prihvatiPonudu",{id: id,nId:this.$route.params.id}).then(()=>{
              this.$store.dispatch("Ponude/dobaviPonude",this.nId);
              this.$emit("odobrena-ponuda");
          });
      },
    },
    props: ["ponude","narudzbenica"],
    emits: ["odobrena-ponuda"]
  }
</script>

<style>

</style>
