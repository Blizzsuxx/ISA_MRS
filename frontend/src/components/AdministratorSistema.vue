<template>
    <el-container style="height: 600px; border: 1px solid #eee">
        <el-aside width="300px" style="background-color: rgb(238, 241, 246)">
            <h5>Opcije</h5>
            <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose">
                <el-submenu index="1">
                    <template #title>
                    <i class="el-icon-location"></i>
                    <span>Korisnici</span>
                    </template>
                    <el-menu-item-group title="Administratori">
                    <el-menu-item index="1-1" @click="promjeniti('AS')">Administratori Sistema</el-menu-item>
                    <el-menu-item index="1-2" @click="promjeniti('AP')">Administratori Apoteka</el-menu-item>
                    </el-menu-item-group>
                    <el-menu-item-group title="Radnici">
                    <el-menu-item index="1-3" @click="promjeniti('Dermatolozi')">Dermatolozi</el-menu-item>
                    <el-menu-item index="1-4" @click="promjeniti('Dobavljaci')">Dobavljaci</el-menu-item>
                    </el-menu-item-group>
                </el-submenu>
            </el-menu>
            <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose">
                <el-submenu index="2">
                    <template #title>
                    <i class="el-icon-location"></i>
                    <span>Lijekovi</span>
                    </template>
                    <el-menu-item-group title="Lijekovi">
                    <el-menu-item index="2-1" @click="dobaviLijekove">Svi Lijekovi</el-menu-item>
                    </el-menu-item-group>
                </el-submenu>
            </el-menu>
            <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose">
                <el-submenu index="3">
                    <template #title>
                    <i class="el-icon-location"></i>
                    <span>Registracije i Kreiranje</span>
                    </template>
                    <el-menu-item-group title="Registracije">
                    <el-menu-item index="3-1" @click="registracija">Korisnici</el-menu-item>
                    </el-menu-item-group>
                    <el-menu-item-group title="Kreiranje">
                    <el-menu-item index="3-2" @click="kreiranjeLijekova">Lijekovi</el-menu-item>
                    <el-menu-item index="3-3" @click="kreiranjeApoteka">Apoteke</el-menu-item>
                    </el-menu-item-group>
                </el-submenu>
            </el-menu>
            <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose">
                <el-submenu index="4">
                    <template #title>
                    <i class="el-icon-location"></i>
                    <span>Dodatne Opcije</span>
                    </template>
                    <el-menu-item-group title="Dodatne Opcije">
                    <el-menu-item index="4-1" @click="odjava">Odjava</el-menu-item>
                    </el-menu-item-group>
                </el-submenu>
            </el-menu>
        </el-aside>
        <el-main>
            <div v-if="indikator == 1">
                <DTOKorisniciTabela ref="tabela" :funkcija="promjeniti" v-bind:tableData="korisnici"/>
            </div>
            <div v-else-if="indikator == 2">
                <DTOLijekoviTabela ref="dijete" :funkcija="dobaviLijekove" v-bind:lijekovi="lijekovi"/>
            </div>
            <div v-else>
            </div>
        </el-main>
    </el-container>
</template>

<script>
import DTOKorisniciTabela from './DTOKorisniciTabela'
import DTOLijekoviTabela from './DTOLijekoviTabela'

export default {
    name: 'AdministratorSistema',
    data()  {
        return {
          korisnici: [],
          lijekovi: [],
          indikator: 0,  
        }
        
    },
    methods: {
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
        registracija(){
            this.$router.push('/ap/FormaKorisnika');
        },
        kreiranjeLijekova(){
            this.$router.push('/ap/FormaLijekova');
        },
        kreiranjeApoteka(){
            this.$router.push('/ap/FormaApoteke');
        },
        odjava(){
            this.$store.dispatch('APKorisnici/logout');
            this.$router.push('/ap/prijava');
        }

    },
    components:{
        DTOKorisniciTabela, DTOLijekoviTabela
    },
    /*
    mounted() {
        this.$store.dispatch("APKorisnici/dobaviAdministratoreApoteka").then(response => {
            this.sadrzaj = this.$store.state.APKorisnici.administratoriApoteke;
            return response;
        })
    },*/

  }
</script>
