
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
          @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>

</template>

<script>
  export default {
    data() {
      return {
        name: 'DTOKorisniciTabela',
        search: '',
      }
    },
    props: ['tableData'],
    methods: {
      handleEdit(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        console.log(index, row);
      }
    },
  }
</script>

<style>

</style>
