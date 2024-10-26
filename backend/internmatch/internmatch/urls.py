import django.conf.urls.static
import django.contrib.admin
import django.urls
import drf_yasg.openapi
import drf_yasg.views
import rest_framework.permissions
import rest_framework_simplejwt.views
from rest_framework import routers

import users.views
import vacancy.views

schema_view = drf_yasg.views.get_schema_view(
    drf_yasg.openapi.Info(title='openApi', default_version='v1'),
    public=True,
    permission_classes=[rest_framework.permissions.AllowAny],
)


router = routers.DefaultRouter()
router.register(r'intern', users.views.InternViewSet)
router.register(r'skill', users.views.SkillViewSet)
router.register(r'emplyer', users.views.EmployerViewSet)
router.register(r'vacancy', vacancy.views.VacancyViewSet)
router.register(r'echo-vacancy', vacancy.views.EchoVacancyViewSet)


urlpatterns = [
    django.urls.path(
        'api/v1/',
        django.urls.include(router.urls)
    ),
    django.urls.path(
        'api/v1/token/',
        rest_framework_simplejwt.views.TokenObtainPairView.as_view(),
        name='token_obtain_pair',
    ),
    django.urls.path(
        'api/v1/token/refresh/',
        rest_framework_simplejwt.views.TokenRefreshView.as_view(),
        name='token_refresh',
    ),
    django.urls.path(
        'api/v1/token/verify/',
        rest_framework_simplejwt.views.TokenVerifyView.as_view(),
        name='token_verify',
    ),
    django.urls.path(
        'api/v1/users/signup/',
        users.views.UserCreateAPIView.as_view(),
        name='users-create',
    ),
    django.urls.path(
        'api/v1/users/list/search/<str:username_filter>/',
        users.views.UsersSearchListApiView.as_view(),
        name='users-list-search',
    ),
    django.urls.path(
        'api/v1/users/list/',
        users.views.UsersListApiView.as_view(),
        name='users-list',
    ),
    django.urls.path(
        'api/v1/profile/',
        users.views.ProfileMyRetrieveAPIView.as_view(),
        name='profile-my-read-update',
    ),
    django.urls.path(
        'api/v1/profile/intern/',
        users.views.InternProfileMyRetrieveAPIView.as_view(),
        name='profile-intern-my-read-update',
    ),
    django.urls.path(
        'api/v1/profile/employer/',
        users.views.EmployerProfileMyRetrieveAPIView.as_view(),
        name='profile-employer-my-read-update',
    ),
    django.urls.path(
        'api/v1/profile/<int:user_id>/',
        users.views.ProfileRetrieveUpdateAPIView.as_view(),
        name='profile-read-update',
    ),
    django.urls.path('admin/', django.contrib.admin.site.urls),
    django.urls.path('api-auth/', django.urls.include('rest_framework.urls')),
    django.urls.path(
        'api/v1/docs/swagger/', schema_view.with_ui('swagger', cache_timeout=0)
    ),
    django.urls.path(
        'api/v1/docs/redoc/', schema_view.with_ui('redoc', cache_timeout=0)
    ),
]

urlpatterns += django.conf.urls.static.static(
    django.conf.settings.MEDIA_URL,
    document_root=django.conf.settings.MEDIA_ROOT,
)
