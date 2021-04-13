<template>
  <el-container style="height: 600px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '3']">
        <el-menu-item index="1">Home</el-menu-item>
        <el-submenu index="1">
          <template #title><i class="el-icon-menu"></i></template>
          <el-menu-item index="1-1">Zapocni Pregled</el-menu-item>
          <el-menu-item index="1-2">Zakazi Pregled</el-menu-item>
          <slot />
        </el-submenu>
        <el-submenu index="2">
          <template #title><i class="el-icon-setting"></i></template>
          <el-menu-item index="2-1">Profil</el-menu-item>
          <el-menu-item index="2-2">Prethodni Klijenti</el-menu-item>
          <el-menu-item index="2-3">Zakazivanje Odmora</el-menu-item>
          <el-menu-item index="2-4">Odjava</el-menu-item>
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
          <el-table
              :data="tableData.filter(data => !search || data.pacijent.firstName.toLowerCase().includes(search.toLowerCase()) || data.pacijent.lastName.toLocaleLowerCase().includes(search.toLocaleLowerCase()))"
              style="width: 100%">
           <el-table-column prop="start" label="Pocinje" width="200" sortable>
          </el-table-column>
          <el-table-column prop="end" label="Zavrsetak" width="200" sortable>
          </el-table-column>
          <el-table-column prop="pacijent.firstName" label="Ime" width="120" sortable>
          </el-table-column>
      <el-table-column prop="pacijent.lastName" label="Prezime" width="120" sortable>
          </el-table-column>
          <el-table-column prop="apoteka.ime" label="Apoteka" width="240" sortable>
          </el-table-column>
            <el-table-column
                align="right">
              <template #header>
                <el-input
                    v-model="search"
                    size="mini"
                    placeholder="Upisite ime i prezime pacijenta"/>
              </template>
              <template #default="scope">
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
export default {
  name: 'APPregledPacijenata',

  methods: {
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
            message: 'Pregled zapocet'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Prekid'
          });
        });
    }
  },

  mounted(){
      //pozivanje ucitavanja podataka poseta
      this.$store.dispatch("APPosete/dobaviPosete")
      this.tableData = this.$store.state.APPosete.svePosete;
      
    },

  data() {

    return {
      tableData: this.$store.state.APPosete.svePosete,
      search: '',
    }
  }
};
</script>