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
              :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()) || data.lastname.toLocaleLowerCase().includes(search.toLocaleLowerCase()))"
              style="width: 100%">
            <el-table-column prop="start" label="Pocinje" width="140">
            </el-table-column>
            <el-table-column prop="end" label="Zavrsetak" width="140">
            </el-table-column>
            <el-table-column prop="name" label="Ime" width="120">
            </el-table-column>
            <el-table-column prop="lastname" label="Prezime" width="120">
            </el-table-column>
            <el-table-column prop="pharmacy" label="Apoteka" width="240">
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
    },
    handlePregled(index, row) {
      console.log(index, row);
    }
  },

  data() {
    const item = {
      start: '05/28/2015 08:30',
      end: '10/28/2015 09:00',
      name: 'John',
      lastname: 'Titor',
      pharmacy: 'No. 189, Grove St, Los Angeles'
    };

    const item2 = {
      start: '05/28/2015 08:30',
      end: '10/28/2015 09:00',
      name: 'a',
      lastname: 'Titor',
      pharmacy: 'No. 189, Grove St, Los Angeles'
    };

    const item3 = {
      start: '05/28/2015 08:30',
      end: '10/28/2015 09:00',
      name: 'John',
      lastname: 'b',
      pharmacy: 'No. 189, Grove St, Los Angeles'
    };

    let a = Array()
    a.push(item)
    a.push(item2)
    a.push(item3)

    return {
      tableData: a,
      storageData: a,
      search: '',
    }
  }
};
</script>