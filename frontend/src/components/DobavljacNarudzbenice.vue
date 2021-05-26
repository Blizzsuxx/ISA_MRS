<template>
  <el-table
    :data="narudzbenice.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%">
    <el-table-column
      label="Id"
      prop="id">
    </el-table-column>
    <el-table-column
      label="Rok"
      prop="rok">
    </el-table-column>
    <el-table-column
      label="Status"
      prop="zavrsena">
    </el-table-column>
    <el-table-column
      label="Admin Apoteke"
      prop="korisnickoImeAdminaApoteke">
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
          @click="sadrzaj(scope.$index, scope.row)">Sadrzaj</el-button>
        <el-button
          size="mini"
          @click="kreirajPonudu(scope.$index, scope.row)">Ponuda</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog
    title="Sadrzaj narudzbenice"
    v-model="prviProzor"
    width="50%"
    destroy-on-close
    center>
    <div style="display: flex;
  justify-content: center;  padding-bottom: 20px;">
    </div>
    <el-table
    :data="narudzbenica.listaStanja.filter(data => !searchOdgovori)"
    style="width: 100%">
    <el-table-column
      label="Cijena"
      prop="cijena">
    </el-table-column>
    <el-table-column
      label="Datum Isteka Cijene"
      prop="datumIstekaCijene">
    </el-table-column>
    <el-table-column
      label="Kolicina"
      prop="kolicina">
    </el-table-column>
    <el-table-column
      label="Naziv Lijeka"
      prop="lijek.naziv">
    </el-table-column>
    <el-table-column
      align="right">
      <template #header>
        <el-input
          v-model="searchOdgovori"
          size="mini"
          placeholder="Type to search"/>
      </template>
     
    </el-table-column>
  </el-table>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="prviProzor = false">Izadji</el-button>
      </span>
    </template>
    </el-dialog>

    <el-dialog
    title="Kreiraj Ponudu"
    v-model="drugiProzor"
    width="50%"
    destroy-on-close
    center>
    <div style="display: flex;
  justify-content: center;  padding-bottom: 20px;">
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
        narudzbenice: [],
        search: '',
        prviProzor: false,
        drugiProzor: false,
        searchOdgovori: '',
        narudzbenica: {},
        ponuda: {

        }
      }
    },
    mounted() {
        this.$store.dispatch("Narudzbenice/dobaviSveNarudzbenice")
        .then(response => {
              this.narudzbenice = response.data;
              console.log(this.narudzbenice);
             
        });
    },
    methods: {
      sadrzaj(index, row) {
        console.log(index, row);
        this.narudzbenica = row;
        this.prviProzor = true;
      },
      kreirajPonudu(index, row) {
        this.narudzbenica = row;
        this.drugiProzor = true;
      }
    },
  }
</script>