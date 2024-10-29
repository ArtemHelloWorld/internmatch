import django.contrib.auth.password_validation
import django.core.exceptions
import django.db
import rest_framework.response
import rest_framework.serializers
import rest_framework.status

import users.models
import users.serializers_external
import vacancy.serializers


class UserSerializer(rest_framework.serializers.ModelSerializer):
    class Meta:
        model = users.models.User
        fields = [
            'id',
            'username',
            'first_name',
            'last_name',
            'password',
        ]

    def validate_password(self, password):
        try:
            django.contrib.auth.password_validation.validate_password(
                password=password
            )
        except django.core.exceptions.ValidationError as e:
            raise rest_framework.serializers.ValidationError(e.messages)
        return password

    def create(self, validated_data):
        password = validated_data.pop('password')
        user = users.models.User(**validated_data)
        user.set_password(password)
        user.save()
        return user


class SkillSerializer(rest_framework.serializers.ModelSerializer):
    class Meta:
        model = users.models.Skill
        fields = [
            'id',
            'intern',
            'name',
            'rate'
        ]


class ProfileSerializer(rest_framework.serializers.ModelSerializer):
    user_type = rest_framework.serializers.SerializerMethodField()

    def get_user_type(self, obj):
        if obj.is_anonymous:
            return None

        if hasattr(obj, 'intern'):
            return 'intern'
        elif hasattr(obj, 'employer'):
            return 'employer'
        return None
    
    class Meta:
        model = users.models.User
        fields = [
            'id',
            'username',
            'email',
            'first_name',
            'last_name',
            'profile_image',
            'user_type'
        ]


class InternProfileSerializer(rest_framework.serializers.ModelSerializer):
    skills = SkillSerializer(many=True, read_only=True)

    class Meta:
        model = users.models.Intern
        fields = [
            'id',
            'skills',
        ]

class EmployerProfileSerializer(rest_framework.serializers.ModelSerializer):
    vacancies = vacancy.serializers.VacancySerializer(many=True, read_only=True)

    class Meta:
        model = users.models.Employer
        fields = [
            'id',
            'vacancies',
            'inn',
        ]

class InternSerializer(rest_framework.serializers.ModelSerializer):
    skills = SkillSerializer(many=True, read_only=True)

    def to_representation(self, instance):
        data = super().to_representation(instance)
        data['user'] = ProfileSerializer(instance.user).data
        return data

    class Meta:
        model = users.models.Intern
        fields = [
            'id',
            'user',
            'skills',
        ]


class EmployerSerializer(rest_framework.serializers.ModelSerializer):

    def to_representation(self, instance):
        data = super().to_representation(instance)
        data['user'] = ProfileSerializer(instance.user).data
        return data

    class Meta:
        model = users.models.Employer
        fields = [
            'id',
            'user',
            'inn',
        ]
