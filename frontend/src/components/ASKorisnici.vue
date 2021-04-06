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
        </el-aside>
        <el-main>
            <DTOKorisniciTabela ref="tabela" v-bind:tableData="nesto"/>
        </el-main>
    </el-container>
</template>

<script>
import DTOKorisniciTabela from './DTOKorisniciTabela'
export default {
    name: 'ASKorisnici',
    data()  {
        return {
          nesto: []  
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
            if (p === "AS"){
                this.$store.dispatch("APKorisnici/dobaviAdministratoreSistema").then(response => {
                    this.nesto = this.$store.state.APKorisnici.administratoriSistema;
                    return response;
                })
            } else if (p === "AP"){
                this.$store.dispatch("APKorisnici/dobaviAdministratoreApoteka").then(response => {
                    this.nesto = this.$store.state.APKorisnici.administratoriApoteke;
                    return response;
                })
            } else if (p === "Dermatolozi"){
                this.$store.dispatch("APKorisnici/dobaviDermatologe").then(response => {
                    this.nesto = this.$store.state.APKorisnici.dermatolozi;
                    return response;
                })
            } else if (p === "Dobavljaci"){
                this.$store.dispatch("APKorisnici/dobaviDobavljace").then(response => {
                    this.nesto = this.$store.state.APKorisnici.dobavljaci;
                    return response;
                })
            }
        },
    },
    components:{
        DTOKorisniciTabela
    },
    mounted() {
        this.$store.dispatch("APKorisnici/dobaviAdministratoreApoteka").then(response => {
            this.nesto = this.$store.state.APKorisnici.administratoriApoteke;
            return response;
        })
    },

  }
</script>
