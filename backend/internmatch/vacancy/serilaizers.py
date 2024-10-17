import rest_framework.serializers

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
        fields = ['employer', 'title', 'description', 'salary', 'duration', 'image']


class EchoVacancySerializer(rest_framework.serializers.ModelSerializer):
    intern = rest_framework.serializers.ReadOnlyField(source='intern.user.username')

    def create(self, validated_data):
        request = self.context.get('request')
        item = vacancy.models.EchoVacancy.objects.create(
            intern=request.user,
            **validated_data
        )
        return item

    class Meta:
        model = vacancy.models.EchoVacancy
        fields = '__all__'
