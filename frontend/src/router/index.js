import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AuthRedirectHandler from '../views/AuthRedirectHandler.vue'

import RecommendView from '@/views/Recommend.vue'
import PostCreateView from '@/views/posts/PostCreateView.vue'
import PostDetailView from '@/views/posts/PostDetailView.vue'
import PostListView from '@/views/posts/PostListView.vue'
import PostEditView from '@/views/posts/PostEditView.vue'
import NotFoundView from '@/views/NotFoundView.vue'
import NestedView from '@/views/nested/NestedView.vue'
import NestedOneView from '@/views/nested/NestedOneView.vue'
import NestedTwoView from '@/views/nested/NestedTwoView.vue'
import NestedHomeView from '@/views/nested/NestedHomeView.vue'
import PlantListView from '@/views/plants/PlantListView.vue'
import PlantDetailView from '@/views/plants/PlantDetailView.vue'
import PlantTestMainView from '@/views/plants/PlantTestMainView.vue'
import PlantTestStartView from '@/views/plants/PlantTestStartView.vue'
import PlantTestView from '@/views/plants/PlantTestView.vue'
import PlantTestResultView from '@/views/plants/PlantTestResultView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/auth/redirect',
    name: 'AuthRedirectHandler',
    component: AuthRedirectHandler
  },
  {
    path: '/recommend',
    name: 'Recommend',
    component: RecommendView
  },
  {
    path: '/posts',
    name: 'PostList',
    component: PostListView
  },
  {
    path: '/posts/create',
    name: 'PostCreate',
    component: PostCreateView
  },
  {
    path: '/posts/:id',
    name: 'PostDetail',
    component: PostDetailView,
    props: true
    // props: route => ({ id: parseInt(route.params.id) }),
  },
  {
    path: '/posts/:id/edit',
    name: 'PostEdit',
    component: PostEditView
  },
  {
    path: '/plants',
    name: 'PlantList',
    component: PlantListView
  },
  {
    path: '/plants/:id',
    name: 'PlantDetail',
    component: PlantDetailView
  },
  {
    path: '/plants/test/main',
    name: 'PlantTestMainView',
    component: PlantTestMainView
  },
  {
    path: '/plants/test/start',
    name: 'PlantTestStartView',
    component: PlantTestStartView
  },
  {
    path: '/plants/test',
    name: 'PlantTestView',
    component: PlantTestView
  },
  {
    path: '/plants/test/result',
    name: 'PlantTestResultView',
    component: PlantTestResultView
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    component: NotFoundView
  },
  {
    path: '/nested',
    name: 'Nested',
    component: NestedView,
    children: [
      {
        path: '',
        name: 'NestedHome',
        component: NestedHomeView
      },
      {
        path: 'one',
        name: 'NestedOne',
        component: NestedOneView
      },
      {
        path: 'two',
        name: 'NestedTwo',
        component: NestedTwoView
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from) => {
  console.log('to: ', to)
  console.log('from: ', from)
  if (to.name === 'MyPage') {
    // router.push({name: 'Home'})
    // return false;
    // return { name: 'Home' };
    return '/posts'
  }
})

export default router
