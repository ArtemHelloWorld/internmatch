from datetime import timedelta

import rest_framework.views
import rest_framework.viewsets
from rest_framework.response import Response
from django.db.models import Q
from django.utils import timezone


import vacancy.models
import vacancy.serializers


class VacancyViewSet(rest_framework.viewsets.ModelViewSet):
    queryset = vacancy.models.Vacancy.objects.all()
    serializer_class = vacancy.serializers.VacancySerializer

    def list(self, request):
        if search_string := request.GET.get('search'):
            query = Q()
            for word in search_string.split():
                query |= Q(employer__user__username__icontains=word) 
                query |= Q(title__icontains=word)
                query |= Q(description__icontains=word)
                query |= Q(skills__icontains=word)
                
                if word.isdigit():
                    integer = int(word)
                    query |= Q(salary__gte=integer * 0.9) & Q(salary__lte=integer * 1.1)
                    query |= Q(duration__gte=integer * 0.9) & Q(duration__lte=integer * 1.1)
                    query |= Q(hours_per_week__gte=integer * 0.9) & Q(hours_per_week__lte=integer * 1.1)
                    
            self.queryset = self.queryset.filter(query)
        return super().list(request)

class EchoVacancyViewSet(rest_framework.viewsets.ModelViewSet):
    queryset = vacancy.models.EchoVacancy.objects.all()
    serializer_class = vacancy.serializers.EchoVacancySerializer

class RecentVacanciesView(rest_framework.views.APIView):
    def get(self, request, *args, **kwargs):
        one_minute_ago = timezone.now() - timedelta(seconds=10)
        recent_vacancies = vacancy.models.Vacancy.objects.filter(time_created__gt=one_minute_ago)
        serializer = vacancy.serializers.VacancySerializer(recent_vacancies, many=True)
        return Response(serializer.data)
