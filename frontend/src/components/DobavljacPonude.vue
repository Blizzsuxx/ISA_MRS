<template>
  <el-table
    :data="ponude.filter(data => !search || data.nazivPonude.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%">
    <el-table-column
      label="Sifra"
      prop="id">
    </el-table-column>
    <el-table-column
      label="Naziv Ponude"
      prop="nazivPonude">
    </el-table-column>
    <el-table-column
      prop="dobavljac"
      label="Dobavljac">
    </el-table-column>
    <el-table-column
      prop="status"
      label="Status">
    </el-table-column>
    <el-table-column
      prop="cijenaPonude"
      label="Cijena Ponude">
    </el-table-column>
    <el-table-column
      align="right">
      <template #header>
        <el-input
          v-model="search"
          size="mini"
          placeholder="Type to search"/>
      </template>
      <template #default="scope">
        <el-button
          size="mini"
          @click="narudzbenicaInfo(scope.$index, scope.row)">Narudzbenica</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  export default {
    data() {
      return {
        ponude: [],
        search: '',
      }
    },
    mounted() {
        this.$store.dispatch("Ponude/dobaviPonudeDobavljaca")
        .then(response => {
            this.ponude = response.data;
        });
    },
    methods: {
      narudzbenicaInfo(index, row){
          console.log(index);
          console.log(row);
      }
      
    },
  }
</script>