<template>
  <el-container style="height: 600px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '3']">
        <el-link :href="'/ap/' + this.$store.state.APKorisnici.trenutnaRedirekcija">
        <el-menu-item index="1">Home</el-menu-item>
        </el-link>
        <el-submenu index="1">
          <template #title><i class="el-icon-menu"></i></template>
          <slot />
        </el-submenu>
        <el-submenu index="2">
          <template #title><i class="el-icon-setting"></i></template>
          <el-link href="/ap/izmena">
            <el-menu-item index="2-1">Profil</el-menu-item>
          </el-link>
          <el-link :href="'/ap/' + this.$store.state.APKorisnici.trenutnaRedirekcija + '/pacijenti'" v-if="this.radnik != null && this.radnik.promenioSifru">
          <el-menu-item index="2-2">Prethodni Klijenti</el-menu-item>
            </el-link>
          <el-link href="/ap/prijava">
          <el-menu-item index="2-4" href="/ap/prijava">Odjava</el-menu-item>
          </el-link>
        </el-submenu>

      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <slot name="header-slot"></slot>
        

        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <template #dropdown>
            <el-dropdown-menu>
                <el-dropdown-item>Profil</el-dropdown-item>
                <el-dropdown-item>Odjava</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        <span>Sulejman Velicanstveni</span>
      </el-header>

      <el-main>
          
        <h3>Pregled svih lijekova</h3> 
        
        <LijekoviTabelaPregled @promjenjena-selekcija="selektujRedove" ref='dijete' v-bind:lijekovi="$store.state.APlijekovi.sviLijekovi" referenca="multipleTable" />
        <div style="margin-top: 20px">
            <el-button type="primary" @click="ocistiSelekciju()" plain>Očisti selekciju</el-button>
            <el-button type="primary" @click="dodeliLekove()" plain>Dodeli Lekove</el-button>
            <el-popconfirm

        title="Da li ste sigurni da želite da zavrsite sa pregledom?"
        >
        <template #reference>
            <el-button type="primary" @click="zavrsiPregled()">Zavrsi pregled</el-button>
        </template>
        </el-popconfirm>
        <el-button @click="otvoriProzor" v-bind:shortcuts="precice" plain type="primary">
            Zakazi Pregled
        </el-button>
        </div>

        <ModalniProzorZakazivanja  ref="prozor" v-bind:radnik="radnik" v-bind:pregledID="pregledID" v-bind:korisnik="korisnik"/>

        <el-input
            type="textarea"
            :rows="2"
            placeholder="Unesite informacije o pregledu ovde"
            v-model="textarea">
        </el-input>

      </el-main>
    </el-container>
  </el-container>
</template>
<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>

<script>
import { defineComponent, ref } from 'vue'
import LijekoviTabelaPregled from  './LijekoviTabelaPregled'
import ModalniProzorZakazivanja from './modal/ModalniProzorZakazivanja'
  export default defineComponent ({
    name: 'APDermatolog',
    components : {
        LijekoviTabelaPregled,
        ModalniProzorZakazivanja,
    },
    async mounted(){
        this.$store.dispatch('APKorisnici/trenutniRadnik');
        await this.$store.dispatch("APlijekovi/dobaviLijekove");
        await this.$store.dispatch("APKorisnici/dobaviDermatologe");
        await this.$store.dispatch("APKorisnici/promeniRedirekciju", "dermatolog");
        
        for(var i = 0; i < this.$store.state.APlijekovi.sviLijekovi.length; i++){
          this.$store.state.APlijekovi.sviLijekovi[i].kolicina = 1;
        }
        this.radnik = this.$store.state.APKorisnici.trenutniRadnik;
        this.$refs.prozor.radnik = this.radnik;
        this.$refs.prozor.pregledID = this.pregledID;
        this.$refs.prozor.korisnik = this.korisnik;
      if(!this.radnik.promenioSifru){
        this.$message({
            type: 'warning',
            message: 'Molimo vas da promenite sifru, kliknite na profil'
          });
      }      
        
        console.log("");
    },
    setup() {
        return {
            
            textarea: ref('')
        }
    },


    methods: {

      otvoriProzor(){

        this.$refs.prozor.modalOpen = true;
        this.$refs.prozor.radnik = this.radnik;
        this.$refs.prozor.pregledID = this.pregledID;
        this.$refs.prozor.korisnik = this.korisnik;

      },


        ocistiSelekciju(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.dijete.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.dijete.$refs.multipleTable.clearSelection();
        }
      },
      async dodeliLekove(){
        function sleep(milliseconds) {
          const date = Date.now();
          let currentDate = null;
          do {
            currentDate = Date.now();
          } while (currentDate - date < milliseconds);
        }
        console.log("aa");
        console.log(this.korisnik)
        if(this.$refs.dijete.multipleSelection.length == 0){
          this.$message({
            type: 'warning',
            message: 'Niste nista izabrali'
          });
          return
        }
        this.$store.dispatch("APlijekovi/proveriAlergije",{lijekovi :this.$refs.dijete.multipleSelection, korisnik :this.korisnik}).
        then(response => {

          console.log(response);
          sleep(1);
        this.greska= this.$store.state.APlijekovi.greska;
        if(this.greska){
          this.poruka = "Pacijent je alergican!";
          this.$message({
            type: 'warning',
            message: 'Pacijent je alergican'
          });
          this.izabraniLijekovi = [];
          return;
        } else {
          this.greaska = false;
        }
        console.log("1")
        console.log(this.$refs.dijete.multipleSelection);
        console.log("3")
        this.$store.dispatch("APlijekovi/proveriDostupnost",{lijekovi: this.$refs.dijete.multipleSelection, pregledID: this.$route.params.pregledID}).
        then(response => {
          console.log(response);
        this.greska=this.$store.state.APlijekovi.greska;
        if(this.greska){
          this.izabraniLijekovi = [];
          this.$store.dispatch("Mail/posaljiMail", {"text" : "zatrazio lekove: " + "lekovi " + "u apoteci: " + "teka " + "koji nisu dostupni!", "address" : "mahajiraaji@gmail.com"});
          
          this.$message({
            type: 'warning',
            message: 'Lek nije dostupan i/ili ste porucili previse'
          });
          console.log(this.$store.state.APlijekovi.zamenaLekovi);
          console.log("AAAAAAAAAAAAAAA")
           for(const [key, value] of Object.entries(this.$store.state.APlijekovi.zamenaLekovi)){
             console.log(key);
             console.log("Kljuc")
            var rec = "lek: " + key + " nije dostupan\r\nZamenite sa:";
            for(var i = 0; i < value.length; i++){
              rec += "\r\n" + value[i].sifra;
            }
            this.$message({
            type: 'zamena',
            message: rec
          });
          } 

          
          return;
        } else {
          this.greaska = false;
        }
          console.log("bb");
          this.$message({
            type: 'success',
            message: 'Uspesno ste dodelili lekove'
          });
          this.izabraniLijekovi = this.$refs.dijete.multipleSelection;
        this.$refs.dijete.$refs.multipleTable.clearSelection();
        this.greska = false;

        });
        


        })
        
      },



      

    
        selektujRedove(val) {
          if(val){
        this.$refs.dijete.multipleSelection = val;
          }
      },

      zavrsiPregled(){
        this.$store.dispatch("ERecepti/postaviRezervaciju", {"pacijent" : this.korisnik, "zapisano" : this.textarea, "pregledID" : this.$route.params.pregledID, "lijekovi" : this.izabraniLijekovi});
        this.$router.push({name: "AP" + this.$store.state.APKorisnici.trenutnaRedirekcija.charAt(0).toUpperCase() + this.$store.state.APKorisnici.trenutnaRedirekcija.slice(1)});
      }
        
    },
        data() {


          console.log("PACIJENT ID: " +this.$route.params.pacijentID );
          console.log("PREGLED ID: " +this.$route.params.pregledID );
          if(isNaN(this.$route.params.pregledID)){
            this.$router.go(-1);
          }
          

      return {
        greska : false,
        prozor: false,
        modalOpen: false,
        poruka : "",
        korisnik : this.$route.params.pacijentID,
        radnik : this.$store.state.APKorisnici.trenutniRadnik,
        pregledID : this.$route.params.pregledID,
        izabraniLijekovi : []
        
      }
    }
  });
</script>