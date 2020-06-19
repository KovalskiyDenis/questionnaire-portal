<template>
    <div>
        <navbar :full-navbar="false"></navbar>
    </div>
</template>

<script>
    import Navbar from 'components/navbar/Navbar.vue'

    export default {
        name: "QuestionnairePage",
        components: {
            Navbar
        }
    }
</script>

<style scoped>

</style>