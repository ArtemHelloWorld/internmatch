import rest_framework.viewsets

import vacancy.models
import vacancy.serilaizers


class VacancyViewSet(rest_framework.viewsets.ModelViewSet):
    queryset = vacancy.models.Vacancy.objects.all()
    serializer_class = vacancy.serilaizers.VacancySerializer


class EchoVacancyViewSet(rest_framework.viewsets.ModelViewSet):
    queryset = vacancy.models.EchoVacancy.objects.all()
    serializer_class = vacancy.serilaizers.EchoVacancySerializer
