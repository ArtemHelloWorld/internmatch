import rest_framework.serializers
import rest_framework.exceptions

import vacancy.models


class VacancySerializer(rest_framework.serializers.ModelSerializer):
    employer = rest_framework.serializers.ReadOnlyField(source='employer.user.username')

    def create(self, validated_data):
        request = self.context.get('request')
        item = vacancy.models.Vacancy.objects.create(
            employer=request.user.employer,
            **validated_data
        )
        return item

    class Meta:
        model = vacancy.models.Vacancy
        fields = ['id', 'employer', 'title', 'description', 'skills', 'salary', 'duration', 'image']


class EchoVacancySerializer(rest_framework.serializers.ModelSerializer):
    intern_username = rest_framework.serializers.ReadOnlyField(source='intern.user.username')
    vacancy_meta = VacancySerializer(source='vacancy', read_only=True)

    def create(self, validated_data):
        request = self.context.get('request')
        if not hasattr(request.user, 'intern'):
            return rest_framework.exceptions.ValidationError(
                'Откликаться могут только стажёры'
            )
        item = vacancy.models.EchoVacancy.objects.create(
            intern=request.user.intern,
            **validated_data
        )
        return item

    class Meta:
        model = vacancy.models.EchoVacancy
        fields = '__all__'
