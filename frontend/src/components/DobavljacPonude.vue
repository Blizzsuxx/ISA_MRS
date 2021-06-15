<template>
  <el-table
    :data="ponude.filter(data => !search || pretraga(data,search) ||  data.nazivPonude.toLowerCase().includes(search.toLowerCase()))"
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
      label="Status" :formatter="vratiStatus">
    </el-table-column>
    <el-table-column
      prop="cijenaPonude"
      label="Cijena Ponude">
    </el-table-column>
    <el-table-column
      prop="rokPonude"
      label="Rok Ponude">
    </el-table-column>
    <el-table-column
      prop="rokNarudzbenice"
      label="Rok Narudzbenice">
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
       
        <el-button v-if='scope.row.statusEdita===true'
          size="mini"
          @click="izmena(scope.$index, scope.row)">Izmena</el-button>
        
        <el-button
          size="mini"
          @click="narudzbenicaInfo(scope.$index, scope.row)">Narudzbenica</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog
    title="Narudzbenica Info"
    v-model="prviProzor"
    width="60%"
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
      <p><strong>Sadrzaj narudzbenice</strong></p>
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
  title="Ponuda Info"
    v-model="drugiProzor"
    width="60%"
    destroy-on-close
    center>
    <div style="display: flex;
  justify-content: center;  padding-bottom: 20px;">
    </div>
    <div>
      <p><strong>Naziv Ponude</strong></p>
      <el-input :model-value='ponuda.nazivPonude' v-model="ponuda.nazivPonude"></el-input>
      <p><strong>Cijena Ponude</strong></p>
      <el-input  :model-value='ponuda.cijenaPonude' v-model.number="ponuda.cijenaPonude"></el-input>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="drugiProzor = false">Izadji</el-button>
        <el-button type="primary" @click="handleEdit">Potvrdi</el-button>
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
        prviProzor: false,
        drugiProzor: false,
        searchOdgovori: '',
        centerDialogVisible: false,
        lijek: {},
        ponuda: {}
      }
    },
    mounted() {
        this.$store.dispatch("Ponude/dobaviPonudeDobavljaca")
        .then(response => {
            this.ponude = response.data;
        });
    },
    methods: {
      vratiStatus(row){
        if (row.status === 0)
          return 'čeka na odgovor';
        else if (row.status === 1)
          return 'odbijena';
        else if (row.status === 2)
          return 'prihvaćena';
      },
      pretraga(data, search){
          if (data.status === 0)
          return "ceka na odgovor".toLowerCase().includes(search.toLowerCase())
        else if (data.status === 1)
          return 'odbijena'.toLowerCase().includes(search.toLowerCase())
        else if (data.status === 2)
          return 'prihvacena'.toLowerCase().includes(search.toLowerCase())
      },
      open1() {
        this.$message({
          showClose: true,
          message: 'Uspješno izmjenjena ponuda.',
          type: 'success'
        });
      },
      open2() {
        this.$message({
          showClose: true,
          message: 'Neuspjesno izmjenjena ponuda.',
          type: 'error'
        });
      },
      narudzbenicaInfo(index, row){
          this.$store.dispatch("Ponude/dobaviNarudzbenicuPonude", row.id)
          .then(response => {
            this.narudzbenica = response.data;
            console.log(this.narudzbenica);
            this.prviProzor = true;
           });
      },
      lijekInfo(index, row){
        this.lijek = row.lijek;
        this.centerDialogVisible = true;
      },
      izmena(index, row){
        this.ponuda.id = row.id;
        this.ponuda.nazivPonude = row.nazivPonude;
        this.ponuda.cijenaPonude = row.cijenaPonude;
        this.drugiProzor = true;
        console.log(index, row);
      },
      handleEdit(){
        if (this.ponuda.nazivPonude === '' || this.ponuda.cijenaPonude <= 0){
          this.open2();
        } else {
          this.$store.dispatch("Ponude/azurirajPonudu", this.ponuda)
          .then(response => {
            this.ponude = response.data;
            this.drugiProzor = false;
           });
        }
      }
    },
  }
</script>