
<template>

  <el-table
    :data="tableData.filter(data => !search || data.ime.toLowerCase().includes(search.toLowerCase()) || 
    data.prezime.toLowerCase().includes(search.toLowerCase()) || data.email.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%">
    <el-table-column
      label="Korisnicko Ime"
      prop="korisnickoIme">
    </el-table-column>
    <el-table-column
      label="Ime"
      prop="ime">
    </el-table-column>
    <el-table-column
      label="Prezime"
      prop="prezime">
    </el-table-column>
    <el-table-column
      label="Rodjendan"
      prop="rodjendan">
    </el-table-column>
    <el-table-column
      label="email"
      prop="email">
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
          @click="handleInfo(scope.$index, scope.row)">Edit</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog
    title="Informacije o Korisniku"
    v-model="centerDialogVisible"
    width="30%"
    destroy-on-close
    center>
    <div style="display: flex;
  justify-content: center;  padding-bottom: 20px;">
    <el-image  style="width: 100px; height: 100px; border: 1px solid;" :src="url" :fit="fit">
      <template #error>
        <div class="image-slot">
          <i class="el-icon-user-solid"></i>
        </div>
      </template>
    </el-image>
    </div>
    <div>
      <p><strong>Korisničko Ime</strong></p>
      <el-input :model-value='korisnik.korisnickoIme' v-model="korisnik.korisnickoIme" readonly></el-input>
      <p><strong>Ime</strong></p>
      <el-input  v-model="korisnik.ime"></el-input>
      <p><strong>Prezime</strong></p>
      <el-input  v-model="korisnik.prezime"></el-input>
      <p><strong>Email</strong></p>
      <el-input :model-value='korisnik.email' v-model="korisnik.email" readonly></el-input>
      <p><strong>Rodjendan</strong></p>
      <el-date-picker type="date" v-model="korisnik.rodjendan"></el-date-picker>
      <p><strong>Uloga</strong></p>
      <el-input :model-value='korisnik.uloga' v-model="korisnik.uloga" readonly></el-input>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">Izadji</el-button>
        <el-button type="primary" @click="handleEdit">Potvrdi</el-button>
      </span>
    </template>

  </el-dialog>

</template>

<script>
  export default {
    data() {
      return {
        name: 'DTOKorisniciTabela',
        search: '',
        centerDialogVisible: false,
        korisnik: {
          email: '', 
          ime: '',
          korisnickoIme: '',
          prezime: '',
          rodjendan: '',
          uloga: ''
          },
        url: ''
      }
    },
    props: ['tableData', 'funkcija'],
    methods: {
      handleInfo(index, row) {
        this.$store.dispatch("APKorisnici/dobaviKorisnika", row.korisnickoIme)
        .then(response => {
              this.korisnik = response.data;
              this.centerDialogVisible = true;
          
              
        });
      },
      handleEdit(){
        this.centerDialogVisible = false;
        this.$store.dispatch("APKorisnici/azurirajKorisnika", this.korisnik)
        .then(response => {
          if (response.data === "ROLE_ADMIN_SISTEMA") {
            this.funkcija("AS");
          } else if (response.data === "ROLE_ADMIN_APOTEKE"){
            this.funkcija("AP");
          } else if (response.data === "ROLE_DERMATOLOG") {
            this.funkcija("Dermatolozi");
          } else if (response.data === "ROLE_DOBAVLJAC") {
            this.funkcija("Dobavljaci");
          }
          return response;
        });
        
      },
      handleDelete(index, row) {
        this.$store.dispatch("APKorisnici/obrisiKorisnika", row.korisnickoIme)
        .then(response => {
          if (response.data === "ROLE_ADMIN_SISTEMA") {
            this.funkcija("AS");
          } else if (response.data === "ROLE_ADMIN_APOTEKE"){
            this.funkcija("AP");
          } else if (response.data === "ROLE_DERMATOLOG") {
            this.funkcija("Dermatolozi");
          } else if (response.data === "ROLE_DOBAVLJAC") {
            this.funkcija("Dobavljaci");
          }
        });
      },
    },
  }
</script>

<style>

</style>
