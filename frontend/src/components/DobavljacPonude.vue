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

</template>

<script>
  export default {
    data() {
      return {
        ponude: [],
        search: '',
        narudzbenica: {},
        prviProzor: false,
        searchOdgovori: '',
        centerDialogVisible: false,
        lijek: {}
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
            console.log(this.narudzbenica);
            this.prviProzor = true;
           });
      },
      lijekInfo(index, row){
        this.lijek = row.lijek;
        this.centerDialogVisible = true;
      }
    },
  }
</script>