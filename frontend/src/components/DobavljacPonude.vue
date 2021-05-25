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

  <el-dialog
    title="Narudzbenica Info"
    v-model="prviProzor"
    width="30%"
    destroy-on-close
    center>
    <div style="display: flex;
  justify-content: center;  padding-bottom: 20px;">
    </div>
    <div>
      <p><strong>Id</strong></p>
      <el-input :model-value='narudzbenica.id' v-model="narudzbenica.id" readonly></el-input>
      <p><strong>Rok</strong></p>
      <el-input  :model-value='narudzbenica.id' v-model="narudzbenica.rok" readonly></el-input>
      <p><strong>Status</strong></p>
      <el-input  :model-value='narudzbenica.zavrsena' v-model="narudzbenica.zavrsena" readonly></el-input>
      <p><strong>Korisnicko Ime Admina Apoteke</strong></p>
      <el-input :model-value='narudzbenica.korisnickoImeAdminaApoteke' v-model="narudzbenica.korisnickoImeAdminaApoteke" readonly></el-input>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="prviProzor = false">Izadji</el-button>
      </span>
    </template>
    </el-dialog>

</template>

<script>
  export default {
    data() {
      return {
        ponude: [],
        search: '',
        narudzbenica: {},
        prviProzor: false
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
          this.$store.dispatch("Ponude/dobaviNarudzbenicuPonude", row.id)
          .then(response => {
            this.narudzbenica = response.data;
            console.log(this.narudzbenica.korisnickoImeAdminaApoteke);
            this.prviProzor = true;
           });
      }
      
    },
  }
</script>