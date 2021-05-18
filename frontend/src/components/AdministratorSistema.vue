<template>
    <el-menu :default-active="activeIndex" 
     class="el-menu-demo" mode="horizontal" @open="handleOpen" @close="handleClose"
     background-color="#409EFF"
    text-color="#fff"
    active-text-color="#D6E3F1">
        <el-submenu index="1" v-if='potvrda'>
            <template #title>
            <i></i>
            <span>Korisnici</span>
            </template>
            <el-menu-item index="1-1" @click="promjeniti('AS')">Administratori Sistema</el-menu-item>
            <el-menu-item index="1-2" @click="promjeniti('AP')">Administratori Apoteka</el-menu-item>
            <el-menu-item index="1-3" @click="promjeniti('Dermatolozi')">Dermatolozi</el-menu-item>
            <el-menu-item index="1-4" @click="promjeniti('Dobavljaci')">Dobavljaci</el-menu-item>
        </el-submenu>
        <el-submenu index="2" v-if='potvrda'>
            <template #title>
            <i></i>
            <span>Apoteke i Lijekovi</span>
            </template>
            <el-menu-item index="2-1" @click="dobaviLijekove">Svi Lijekovi</el-menu-item>
            <el-menu-item index="2-1" @click="dobaviApoteke">Sve Apoteke</el-menu-item>
        </el-submenu>
        <el-submenu index="3" v-if='potvrda'>
            <template #title>
            <i></i>
            <span>Registracije i Kreiranje</span>
            </template>
            <el-menu-item index="3-1" @click="registracija">Korisnici</el-menu-item>
            <el-menu-item index="3-2" @click="kreiranjeLijekova">Lijekovi</el-menu-item>
            <el-menu-item index="3-3" @click="kreiranjeApoteka">Apoteke</el-menu-item>
        </el-submenu>
        <el-submenu index="4" v-if='potvrda'>
            <template #title>
            <i></i>
            <span>Loyalty Programa</span>
            </template>
            <el-menu-item index="4-1" @click="loyaltyPrograma">Loyalty Programa</el-menu-item>
        </el-submenu>
        <el-submenu index="5" v-if='potvrda'>
            <template #title>
            <i></i>
            <span>Zalbe</span>
            </template>
            <el-menu-item index="5-1" @click="pregledZalbi">Pregled Zalbi</el-menu-item>
            <el-menu-item index="5-2" @click="istorijaZalbi">Istorija Zalbi</el-menu-item>
        </el-submenu>
        <el-submenu index="6">
            <template #title>
            <i></i>
            <span>Dodatne Opcije</span>
            </template>
            <el-menu-item index="6-1" @click="odjava">Odjava</el-menu-item>
        </el-submenu>
    </el-menu>
    <el-main>
        <div v-if="indikator == 1">
            <DTOKorisniciTabela ref="tabela" :funkcija="promjeniti" v-bind:tableData="korisnici"/>
        </div>
        <div v-else-if="indikator == 2">
            <DTOLijekoviTabela ref="dijete" :funkcija="dobaviLijekove" v-bind:lijekovi="lijekovi"/>
        </div>
        <div v-else-if="indikator == 3">
            <LoyaltyPrograma/>
        </div>
        <div v-else-if="indikator == 4">
            <FormaApoteke/>
        </div>
        <div v-else-if="indikator == 5">
            <FormaKorisnika :izmjeniIndikator="izmjeniIndikator"/>
        </div>
        <div v-else-if="indikator == 6">
            <FormaLijekova :izmjeniIndikator="izmjeniIndikator"/>
        </div>
        <div v-else-if="indikator == 8">
            <PotvrdaLozinke :izmjeniPotvrdu="izmjeniPotvrdu"/>
        </div>
        <div v-else-if="indikator == 9">
            <ZalbePregled/>
        </div>
        <div v-else>
        </div>
    </el-main>
</template>

<script>
import DTOKorisniciTabela from './DTOKorisniciTabela'
import DTOLijekoviTabela from './DTOLijekoviTabela'
import LoyaltyPrograma from './LoyaltyPrograma'
import FormaApoteke from './FormaApoteke'
import FormaKorisnika from './FormaKorisnika'
import FormaLijekova from './FormaLijekova'
import PotvrdaLozinke from './PotvrdaLozinke'
import ZalbePregled from './ZalbePregled'

export default {
    name: 'AdministratorSistema',
    data()  {
        return {
          korisnici: [],
          lijekovi: [],
          indikator: 0,
          potvrda: false,  
          zalbe: []
        }
        
    },
    mounted() {
        this.$store.dispatch("APKorisnici/potvrdaLozinke").
        then(response => {
            if (!response){
                this.indikator = 8;
                this.open3();
            } else {
                this.potvrda = true;
            }
        });
    },
    methods: {
        open1() {
        this.$message({
          showClose: true,
          message: 'Uspjesno izmjenjena lozinka.',
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
        open3() {
        this.$message({
          showClose: true,
          message: 'Prilikom prve primjene lozinku je potrebno izmjeniti.',
          type: 'info'
        });
        },
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        promjeniti(p){
            this.indikator = 1;
            if (p === "AS"){
                this.$store.dispatch("APKorisnici/dobaviAdministratoreSistema").then(response => {
                    this.korisnici = this.$store.state.APKorisnici.administratoriSistema;
                    return response;
                })
            } else if (p === "AP"){
                this.$store.dispatch("APKorisnici/dobaviAdministratoreApoteka").then(response => { 
                    this.korisnici = this.$store.state.APKorisnici.administratoriApoteke;
                    return response;
                })
            } else if (p === "Dermatolozi"){
                this.$store.dispatch("APKorisnici/dobaviDermatologeAdminSistema").then(response => {
                    this.korisnici = this.$store.state.APKorisnici.dermatolozi;
                    return response;
                })
            } else if (p === "Dobavljaci"){
                this.$store.dispatch("APKorisnici/dobaviDobavljace").then(response => {
                    this.korisnici = this.$store.state.APKorisnici.dobavljaci;
                    return response;
                })
            }
        },
        dobaviLijekove(){
            this.indikator = 2;
            this.$store.dispatch('APlijekovi/dobaviDTOLijekove')
            .then(response => {
                this.lijekovi = this.$store.state.APlijekovi.dtoLijekovi;
                return response;
            })
        },
        dobaviApoteke(){
            
        },
        registracija(){
            this.indikator = 5;
        },
        kreiranjeLijekova(){
            this.indikator = 6;
        },
        kreiranjeApoteka(){
            this.indikator = 4;
        },
        loyaltyPrograma(){
            this.indikator = 3;
        },
        odjava(){
            this.$store.dispatch('APKorisnici/logout');
            this.$router.push('/ap/prijava');
        },
        izmjeniIndikator(broj, nesto){
            this.indikator = broj;
            if (this.indikator === 1){
                this.promjeniti(nesto);
            } else if (this.indikator === 2){
                this.dobaviLijekove();
            } else if (this.indikator === 7){
                this.dobaviApoteke();
            }
        },
        izmjeniPotvrdu(izmjena){
            this.potvrda = izmjena;
            if(this.potvrda){
                this.indikator = 0;
                this.open1();
            } else {
                this.open2();
            }
        },
        pregledZalbi(){
           this.indikator = 9;

        },
        istorijaZalbi(){
            this.indikator = 9;
        }

    },
    components:{
        DTOKorisniciTabela, DTOLijekoviTabela, LoyaltyPrograma, FormaApoteke, FormaKorisnika, FormaLijekova,
        PotvrdaLozinke, ZalbePregled
    },
    
  }
</script>
