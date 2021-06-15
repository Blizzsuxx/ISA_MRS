

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
        
        <Selecter options="Nedeljno;Mesecno;Godisnje" style="align: left" @change="promena"></Selecter>

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
        <el-table :data="tableData">
          <el-table-column prop="pocetak" label="Pocinje" width="200">
          </el-table-column>
          <el-table-column prop="kraj" label="Zavrsetak" width="200">
          </el-table-column>
          <el-table-column prop="pacijent.firstName" label="Ime" width="120">
          </el-table-column>
      <el-table-column prop="pacijent.lastName" label="Prezime" width="120">
          </el-table-column>
          <el-table-column prop="apoteka.ime" label="Apoteka" width="240">
          </el-table-column>

          <el-table-column
              align="right">
            <template #default="scope" v-if="this.radnik != null && this.radnik.promenioSifru">
              <el-button
                  size="mini"
                  type="info"
                  @click="handleInfo(scope.$index, scope.row)">Informacije</el-button>
              <el-button
                  size="mini"
                  type="danger"
                  @click="handleOdsustvo(scope.$index, scope.row)">Oznaci odsustvo</el-button>
              <el-button
                  size="mini"
                  type="success"
                  @click="handlePregled(scope.$index, scope.row)">Zapocni pregled</el-button>
            </template>
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
import Selecter from './Selecter.vue';
import APGodisnjiOdmor from './modal/APGodisnjiOdmor'
  export default {
    name: 'APDermatolog',
    components : {
        Selecter,
        APGodisnjiOdmor
    },

    async created(){
      //pozivanje ucitavanja podataka poseta
      console.log("B");
      await this.$store.dispatch('APKorisnici/trenutniRadnik');
      await this.$store.dispatch("APPosete/dobaviPoseteAktivne");
      await this.$store.dispatch("APKorisnici/promeniRedirekciju", "dermatolog");
      this.tableData = this.$store.state.APPosete.svePosete;
      this.radnik = this.$store.state.APKorisnici.trenutniRadnik;
      console.log(this.radnik.promenioSifru)
      if(!this.radnik.promenioSifru){
        alert("Molimo vas da promenite sifru, kliknite na profil");
      }      
    },

    methods: {
      
      kliknut(){
        this.$refs.prozor.modalOpen = true;
        console.log("A")
      },

      handleInfo(index, row) {
      console.log(index, row);
    },
    handleOdsustvo(index, row) {
      console.log(index, row);
      this.$confirm('Da li ste sigurni?', 'Warning', {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {

          this.$store.dispatch("APPosete/zabeleziOdsustvo", {"id" : row.id});
          this.tableData.splice(index, 1);
          console.log("BBBB");


          this.$message({
            type: 'success',
            message: 'Pacijent je zabelezen kao odsutan'
          });

          

        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Prekid'
          });
        });
    },
    handlePregled(index, row) {
      console.log(index, row);
      this.$confirm('Zelite da zapocnete pregled?', 'Warning', {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: 'Pregled zapocet',
          });

          this.getPacijent(row);
          console.log(row);
          this.$router.push({ name: 'APPregled', params: {pacijentID: row.pacijent.username, pregledID: row.id} });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Prekid'
          });
        });
    },

    async getPacijent(row){

      await this.$store.dispatch('APPacijenti/pacijentZaPrelged', row.pacijent);
    },

        promena(value){
            let time = 7;
            if(value === "Mesecno"){
                time = 30;
            }
            if(value === "Godisnje"){
                time = 365;
            }
            
            this.tableData = this.$store.state.APPosete.svePosete.filter((item) => {
              
                return time >= ((new Date(Date.parse(item.kraj))).getTime() -  (new Date(Date.parse(item.pocetak))).getTime()) / (1000 * 3600 * 24)} );
        }
    },
        data() {


      return {
        tableData: this.$store.state.APPosete.svePosete,
        radnik: this.$store.state.APKorisnici.trenutniRadnik,
        
      }
    }
  };
</script>