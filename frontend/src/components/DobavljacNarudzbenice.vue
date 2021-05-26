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
          @click="ponudaProzor(scope.$index, scope.row)">Ponuda</el-button>
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
    <p><strong>Naziv Ponude</strong></p>
    <el-input v-model="ponuda.nazivPonude"></el-input>
    <p><strong>Cijena Ponude</strong></p>
    <el-input  v-model.number="ponuda.cijenaPonude"></el-input>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="drugiProzor = false">Izadji</el-button>
        <el-button type="primary" @click="kreirajPonudu">Potvrdi</el-button>
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
            nazivPonude: '',
            status: 0,
            cijenaPonude: 0,
            idNarudzbenice: 0,
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
      open1() {
        this.$message({
          showClose: true,
          message: 'Uspjesno kreirana ponuda.',
          type: 'success'
        });
        },
    open2() {
        this.$message({
          showClose: true,
          message: 'Podaci nisu validni.',
          type: 'error'
        });
        },

      sadrzaj(index, row) {
        console.log(index, row);
        this.narudzbenica = row;
        this.prviProzor = true;
      },
      ponudaProzor(index, row) {
        this.narudzbenica = row;
        this.drugiProzor = true;
      },
      kreirajPonudu(){
        if (this.ponuda.nazivPonuda === '' || this.ponuda.cijenaPonude <= 0){
            this.open2();
        } else {
            this.drugiProzor = false;
            this.ponuda.idNarudzbenice = this.narudzbenica.id;
            this.$store.dispatch("Ponude/kreirajPonudu", this.ponuda)
            .then(response => {
                console.log(response.data);
                this.open1();
                this.drugiProzor = false;
             
            });
        }
      }
    },
  }
</script>