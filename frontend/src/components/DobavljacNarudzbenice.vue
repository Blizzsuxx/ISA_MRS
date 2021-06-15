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
        <template #default="scope">
        <el-button
          size="mini"
          @click="lijekInfo(scope.$index, scope.row)">Lijek</el-button>
      </template>
    </el-table-column>
  </el-table>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="prviProzor = false">Izadji</el-button>
      </span>
    </template>

    <el-dialog
    title="Informacije o Lijeku"
    v-model="centerDialogVisible"
    width="30%"
    destroy-on-close
    center>
    <div style="display: flex;
  justify-content: center;  padding-bottom: 20px;">
    <el-image  style="width: 100px; height: 100px; border: 1px solid;" :src="url" :fit="fit">
      <template #error>
        <div class="image-slot">
          <i class="el-icon-info"></i>
        </div>
      </template>
    </el-image>
    </div>
    <div>
      <p><strong>Naziv</strong></p>
      <el-input :model-value='lijek.naziv' v-model="lijek.naziv" readonly></el-input>
      <p><strong>Napomena</strong></p>
      <el-input  v-model="lijek.napomena" readonly></el-input>
      <p><strong>Oblik Lijeka</strong></p>
      <el-input  v-model="lijek.oblikLijeka" readonly></el-input>
      <p><strong>Ocijena</strong></p>
      <el-input v-model="lijek.ocijena" readonly></el-input>
      <p><strong>Proizvodjac</strong></p>
      <el-input v-model="lijek.proizvodjac" readonly></el-input>
      <p><strong>Vrsta Lijeka</strong></p>
      <el-input v-model="lijek.vrstaLijeka" readonly></el-input>
      <p><strong>Sastav</strong></p>
      <el-input v-model="lijek.sastav" readonly></el-input>
      <p><strong>Poeni</strong></p>
      <el-input v-model.number="lijek.poeni" readonly></el-input>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">Izadji</el-button>
      </span>
    </template>

  </el-dialog>
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
    <p><strong>Rok Ponude</strong></p>
    <el-date-picker type="date" placeholder="Rok Ponude" v-model="ponuda.rokPonude" style="width: 100%;"></el-date-picker>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="drugiProzor = false">Izadji</el-button>
        <el-button type="primary" @click="kreirajPonudu">Potvrdi</el-button>
      </span>
    </template>
    </el-dialog>

</template>

<script>
import moment from 'moment'
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
            rokPonude: ''
        },
        lijek: {},
        centerDialogVisible: false
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
      formirajZavrsena(row){
        if(row.prihvacena===0){
          return "Ceka na odgovor"
        }
        if(row.prihvacena===1){
          return "Nije prihvacena"
        }
        if(row.prihvacena===2)
          return "Prihvacena"
        return "Ceka na odgovor"
        },
      open3() {
        this.$message({
          showClose: true,
          message: 'Problem sa datumom ili sadrzajem.',
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
        if (this.ponuda.nazivPonuda === '' || this.ponuda.cijenaPonude <= 0 || this.ponuda.rokPonude === ''){
            this.open2();
        } else {
            this.drugiProzor = false;
            this.ponuda.idNarudzbenice = this.narudzbenica.id;
            this.ponuda.rokPonude = moment(String(this.ponuda.rokPonude)).format('YYYY-MM-DD');
            this.$store.dispatch("Ponude/kreirajPonudu", this.ponuda)
            .then(response => {
                if (response.data){
                    this.open1();
                    this.drugiProzor = false;
                } else {
                    this.open3();
                } 
            });
        }
      },
      lijekInfo(index, row){
        this.lijek = row.lijek;
        this.centerDialogVisible = true;
      }
    },
  }
</script>