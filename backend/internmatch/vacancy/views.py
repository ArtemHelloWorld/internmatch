import rest_framework.viewsets

import vacancy.models
import vacancy.serializers


class VacancyViewSet(rest_framework.viewsets.ModelViewSet):
    queryset = vacancy.models.Vacancy.objects.all()
    serializer_class = vacancy.serializers.VacancySerializer


class EchoVacancyViewSet(rest_framework.viewsets.ModelViewSet):
    queryset = vacancy.models.EchoVacancy.objects.all()
    serializer_class = vacancy.serializers.EchoVacancySerializer
