import {createRouter, createWebHistory} from "vue-router/dist/vue-router";
import First from '@/components/views/First'
import EasyQuery from '@/components/views/EasyQuery'

const routerHistory = createWebHistory()

const routes=[
    {
        path: '/first',
        name: "First",
        component: First
    },{
        path: '/ezq',
        name: "EasyQuery",
        component: EasyQuery
    }
]

const router = createRouter({
    history: routerHistory,
    routes
})

export default router