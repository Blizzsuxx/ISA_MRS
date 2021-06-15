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
          <el-menu-item index="2-3" @click="kliknut" v-if="this.radnik != null && this.radnik.promenioSifru">Zakazivanje Odmora</el-menu-item>
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
            <el-input-number v-model="num" controls-position="right" @change="handleChange"></el-input-number>
            <el-button type="primary" icon="el-icon-search" @click="clicked">Trazi</el-button>
            <el-button v-if="this.$store.state.RezervisaniLekovi.rezervisaniZaId != ''" type="primary" @click="izdajLek">Izdaj Lek</el-button>
        <el-table
            :data="this.$store.state.RezervisaniLekovi.rezervisaniZaId"
            style="width: 100%"
            >
            <el-table-column
            property="nazivLeka"
            label="Naziv leka"
            width="200">
            </el-table-column>
            <el-table-column
            property="vrstaLeka"
            label="Vrsta leka"
            width="200">
            </el-table-column>
            <el-table-column
            property="kolicina"
            label="Kolicina"
            width="120">
            </el-table-column>
            <el-table-column
            property="nazivApoteke"
            label="Naziv apoteke"
            width="200">
            </el-table-column>
            <el-table-column
            property="datumVazenja"
            label="Datum vazenja rezervacije"
            width="240">
            </el-table-column>
            
            <el-table-column
            property="cena"
            label="Cena"
            width="120"
            show-overflow-tooltip>
            </el-table-column>
        </el-table>
        <APGodisnjiOdmor  ref="prozor" />
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
import APGodisnjiOdmor from './modal/APGodisnjiOdmor'
  export default {
    name: 'APIzdavanje',
    components : {
        APGodisnjiOdmor
    },


    data() {

      return {
        num: 1,
      }
    },

    mounted(){
      //pozivanje ucitavanja podataka poseta
      this.num = 1;
      
    },

    methods: {

      kliknut(){
        this.$refs.prozor.modalOpen = true;
        console.log("A")
      },

      handleChange(value) {
        console.log(value);
        
      },


      izdajLek(){
        alert("uspesno ste izdali lek!");
        this.$store.dispatch("RezervisaniLekovi/IzdajLek", {"id" : this.num});
        this.$store.dispatch("Mail/posaljiMail", {"text": "pokupili ste lek broj " + this.num, "address" : "mahajiraaji@gmail.com"})
        this.$store.state.RezervisaniLekovi.rezervisaniZaId = "";

      },
    
        clicked(){
            console.log(this.$store.state.RezervisaniLekovi.rezervisaniZaId);
            this.$store.dispatch("RezervisaniLekovi/lekoviPoRezervaciji", {"apoteka" : 1, "id" : this.num});
            
        }
      
    },
  };
</script>